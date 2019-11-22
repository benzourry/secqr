package com.benzourry.secqr;

import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class SecqrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecqrApplication.class, args);
	}

	// This method produce qr using token
	@GetMapping(value = "qr", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> createQR(@RequestParam(value = "code") String code,
										   @RequestParam(value="h", defaultValue = "256") int h,
										   @RequestParam(value = "w", defaultValue = "256") int w){

		try {
			// This token OTP will be verified by the mobile counterpart.
			// Mobile counterpart will post request to Ping! directly to notify the console
			//
			Totp totp = new Totp(Base32.encode(("**shared-secret**").getBytes()));
			return ResponseEntity.ok().cacheControl(CacheControl.maxAge(5, TimeUnit.MINUTES))
					.body(Helper.generateQRCode(totp.now(), w,h));
		} catch (Exception ex) {
			throw new RuntimeException("Error while generating QR code image.", ex);
		}

	}

	// save log record to database (verification no more needed here)
	@PostMapping(value = "log")
	public Map logRecord(@RequestBody Map log){
		return log; // save record to database
	}

}
