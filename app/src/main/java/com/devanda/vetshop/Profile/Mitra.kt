package com.devanda.vetshop.Profile

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
import com.devanda.vetshop.Home.Doctor
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_mitra.*
import java.util.*

class Mitra : AppCompatActivity(), PermissionListener {

    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd:Boolean = false
    var checker:Boolean = false
    lateinit var filePath: Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        mFirebaseDatabase = mFirebaseInstance.getReference("Doctor")

        iv_add_cp.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                iv_add_cp.setImageResource(R.drawable.ic_mitra_add)
                iv_profile_cp.setImageResource(R.drawable.ic_mitra_pic)
                checker = false
            } else {
                ImagePicker.with(this)
                    .cropSquare()
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
            }
        }

        btn_save_cp.setOnClickListener {
            if (statusAdd){
                if (filePath != null) {
                    val progressDialog = ProgressDialog(this)
                    progressDialog.setTitle("Uploading...")
                    progressDialog.show()

                    val ref = storageReference.child("doctors/" + UUID.randomUUID().toString())
                    ref.putFile(filePath)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this@Mitra, "Uploaded", Toast.LENGTH_SHORT).show()

                            ref.downloadUrl.addOnSuccessListener {
                                preferences.setValues("link", it.toString())
                            }
                        }
                        .addOnFailureListener { e ->
                            progressDialog.dismiss()
                            Toast.makeText(this@Mitra, "Failed " + e.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        .addOnProgressListener { taskSnapshot ->
                            val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
                                .totalByteCount
                            progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                        }
                    checker = true
                }
            } else{
                checker = false
                Toast.makeText(this, "Please Upload Picture", Toast.LENGTH_SHORT).show()
            }
        }

        mitra_back_btn.setOnClickListener {
            finish()
        }

        mitra_back_text.setOnClickListener {
            finish()
        }

        mitra_daftar.setOnClickListener {
            var sNama = mitra_nama.text.toString()
            var sAlamat = mitra_alamat.text.toString()
            var sJam = mitra_jam.text.toString()
            var sInfo = mitra_info.text.toString()
            var sTelefon = mitra_telefon.text.toString()
            var sImage = preferences.getValues("link")
            saveProject(sNama, sAlamat, sJam, sInfo, sTelefon, sImage)
        }
    }

    private fun saveProject(sNama: String?, sAlamat: String?, sJam: String?, sInfo: String?,
                            sTelefon: String?, sImage: String?) {
        val doctor = Doctor()
        doctor.nama = sNama
        doctor.alamat = sAlamat
        doctor.praktek = sJam
        doctor.info = sInfo
        doctor.telefon = sTelefon
        doctor.images = sImage

        if (sNama != null) {
            checkingUsername(sNama, doctor)
        }
    }

    private fun checkingUsername(iTitle: String, data: Doctor) {
        mFirebaseDatabase.child(iTitle).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(Doctor::class.java)
                if (user == null) {
                    mFirebaseDatabase.child(iTitle).setValue(data)
                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("alamat", data.alamat.toString())
                    preferences.setValues("jam", data.praktek.toString())
                    preferences.setValues("info", data.info.toString())
                    preferences.setValues("telefon", data.telefon.toString())
                    preferences.setValues("images", data.images.toString())
                    preferences.setValues("status", "1")

                    val intent = Intent(this@Mitra,
                        Success::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Mitra, "Title Used", Toast.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Mitra, ""+error.message, Toast.LENGTH_LONG).show()
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
                .into(iv_profile_cp)

            iv_add_cp.setImageResource(R.drawable.ic_mitra_delete)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}
