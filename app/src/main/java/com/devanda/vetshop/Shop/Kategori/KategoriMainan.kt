package com.devanda.vetshop.Shop.Kategori

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.devanda.vetshop.R
import com.devanda.vetshop.Shop.Item.Mainan
import com.devanda.vetshop.Shop.Posted
import com.devanda.vetshop.Utils.Preferences
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_sell.*
import java.util.*

class KategoriMainan : AppCompatActivity(), PermissionListener {

    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd:Boolean = false
    lateinit var filePath: Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        mFirebaseDatabase = mFirebaseInstance.getReference("Mainan")

        sell_add.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                sell_add.setImageResource(R.drawable.ic_mitra_add)
                sell_pic.setImageResource(R.drawable.ic_mitra_pic)
            } else {
                ImagePicker.with(this)
                    .cropSquare()
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
            }
        }

        sell_save.setOnClickListener {
            if (statusAdd){
                if (filePath != null) {
                    val progressDialog = ProgressDialog(this)
                    progressDialog.setTitle("Uploading...")
                    progressDialog.show()

                    val ref = storageReference.child("products/" + UUID.randomUUID().toString())
                    ref.putFile(filePath)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this@KategoriMainan, "Uploaded", Toast.LENGTH_SHORT).show()

                            ref.downloadUrl.addOnSuccessListener {
                                preferences.setValues("linkproducts", it.toString())
                            }
                        }
                        .addOnFailureListener { e ->
                            progressDialog.dismiss()
                            Toast.makeText(this@KategoriMainan, "Failed " + e.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        .addOnProgressListener { taskSnapshot ->
                            val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                                .totalByteCount
                            progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                        }
                    statusAdd = true
                }
            } else{
                statusAdd = false
                Toast.makeText(this, "Mohon Masukan Gambar", Toast.LENGTH_SHORT).show()
            }
        }

        sell_back_btn.setOnClickListener {
            finish()
        }

        sell_back_text.setOnClickListener {
            finish()
        }

        sell_jual.setOnClickListener {
            if (statusAdd){
                var sNama = sell_nama.text.toString()
                var sHarga = sell_harga.text.toString()
                var sDeskripsi = sell_deskripsi.text.toString()
                var sImage = preferences.getValues("linkproducts")
                saveProject(sNama, sHarga, sDeskripsi, sImage)
            } else{
                Toast.makeText(this, "Mohon Simpan Gambar", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun saveProject(sNama: String?, sHarga: String?, sDeskripsi: String?, sImage: String?) {
        val mainan = Mainan()
        mainan.nama = sNama
        mainan.harga = sHarga
        mainan.deskripsi = sDeskripsi
        mainan.images = sImage

        if (sNama != null) {
            checkingUsername(sNama, mainan)
        }
    }

    private fun checkingUsername(iTitle: String, data: Mainan) {
        mFirebaseDatabase.child(iTitle).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val kat = dataSnapshot.getValue(Mainan::class.java)
                if (kat == null) {
                    mFirebaseDatabase.child(iTitle).setValue(data)
                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("harga", data.harga.toString())
                    preferences.setValues("deskripsi", data.deskripsi.toString())
                    preferences.setValues("images", data.images.toString())
                    preferences.setValues("status", "1")

                    val intent = Intent(this@KategoriMainan,
                        Posted::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@KategoriMainan, "Nama Sudah Digunakan", Toast.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KategoriMainan, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        //To change body of created functions use File | Settings | File Templates.
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

    }

    override fun onPermissionRationaleShouldBeShown(
        permission: com.karumi.dexter.listener.PermissionRequest?,
        token: PermissionToken?
    ) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this, "You cant add profile photo", Toast.LENGTH_LONG ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            statusAdd = true
            filePath = data?.data!!
            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(sell_pic)

            sell_add.setImageResource(R.drawable.ic_mitra_delete)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

}
