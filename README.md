# Problem with short-lived QR using timestamp

Timestamp is usually used to generate short-lived QR. However, this could be easily tampered with by mimicking the token format and synchronize the timestamp. The verification also required issuer server to ensure time-consistency. However, the success rate could be an issue and largely depending on the network speed since the verification should be done within the time-windows.

# Short-lived QR using TOTP 

TOTP is a time-bounded OTP which could be used to generate secured value. This solution also solve the 'time-out' or 'expiry' problem with timestamp approach. The verification is done by the counterpart mobile app using simple OTP verification method with the shared secret with the server. The verification status will then be synchronized with the back-end for recording purpose. To notify the QR console, mobile counterpart can POST to Ping! directly which is more tolerant to network failure.

This approach also eliminate the need for online verification. The verification can be performed entirely offline.
