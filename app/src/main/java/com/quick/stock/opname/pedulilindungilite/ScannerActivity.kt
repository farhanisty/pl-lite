package com.quick.stock.opname.pedulilindungilite

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.quick.stock.opname.pedulilindungilite.databinding.ActivityScannerBinding

class ScannerActivity : AppCompatActivity() {

    private lateinit var codeScan: CodeScanner
    private lateinit var binding: ActivityScannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_scanner)

        binding.codeScanner.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        //Function Code Scanner
        codeScanner()

        //Permission
        setPermission()


    }

    private fun codeScanner() {
        codeScan = CodeScanner(this, findViewById(R.id.code_scanner))

        codeScan.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    startActivity(Intent(this@ScannerActivity, MainActivity::class.java))
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScan.startPreview()
    }

    override fun onPause() {
        codeScan.releaseResources()
        super.onPause()
    }

    private fun setPermission() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeReq()
        }
    }

    private fun makeReq() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA), 101
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            101 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Dibutuhkan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}