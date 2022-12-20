var salt = "mySalt";
var iv = "myIV";
var parsedIV = CryptoJS.enc.Hex.parse(iv);

function capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}

jQuery(document).ready(function ($) {
	$('#submitPasswort').click(function () {
    var password = $('#password').val();
		password = capitalize(password);

    var key = CryptoJS.PBKDF2(
              password,
              CryptoJS.enc.Hex.parse(salt),
              { keySize: this.keySize, iterations: this.iterationCount }
          );
  	var e = CryptoJS.Rabbit.encrypt(password, key, { iv: parsedIV });
    var enc = e.ciphertext.toString(CryptoJS.enc.Base64);

		if (enc === "vcVsCU04vg+x+w==") {
			doris(password, true);
			return;
		} else if (enc === "ud5rHg==") {
			kira(password, true);
			return
		} else if (enc === "qcJmDA==") {
			lea(password, true);
			return
		} else if (enc === "vt52E1Iv") {
			anne(password, true);
			return
		}

		//cancel the submit button default behaviours
		return false;
	});
});


function doris(password, decode) {
  if (decode)
	 setText(encDoris, password);
}

function lea(password, decode) {
  if (decode)
	 setText(encLea, password);
}

function kira(password, decode) {
  if (decode)
	 setText(encKira, password);
}

function anne(password, decode) {
  if (decode)
	 setText(encAnne, password);
}

function setText(text, password) {
	var key = CryptoJS.PBKDF2(
            password,
            CryptoJS.enc.Hex.parse(salt),
            { keySize: this.keySize, iterations: this.iterationCount }
        );

	var cipherParams = CryptoJS.lib.CipherParams.create(
      { ciphertext: CryptoJS.enc.Base64.parse(text) }
  );
  var d = CryptoJS.Rabbit.decrypt(
      cipherParams,
      key,
      { iv: parsedIV }
  );

	jQuery(document).ready(function ($) {
		$('#decoded').html(d.toString(CryptoJS.enc.Utf8));
	});
}
