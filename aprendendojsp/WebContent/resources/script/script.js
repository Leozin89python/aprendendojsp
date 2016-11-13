function iniciarRecorte(){
	    setTimeout(
		   		function() {
				 $('#target').Jcrop({
						onSelect : setCoordinates
					});
				},
		    2000);
	}

	function setCoordinates(c) {
		document.getElementById("x").value = c.x;
		document.getElementById("y").value = c.y;
		document.getElementById("w").value = c.w;
		document.getElementById("h").value = c.h;
	};
	function checkCoordinates() {
		if (document.getElementsByName("x").value == "" || document.getElementsByName("y").value == "") {
			alert("Selecione uma região de corte.");
			return false;
		} else {
			return true;
		}
	};
	
	 function visualizarImg() {
		 var preview = document.querySelector('img');
		  var file    = document.querySelector('input[type=file]').files[0];
		  var reader  = new FileReader();

		  reader.onloadend = function () {
		    preview.src = reader.result;// carrega em base64 a img
		    document.getElementById("base64").value = reader.result;
		  };

		  if (file) {
		    reader.readAsDataURL(file);		    
		  } else {
		    preview.src = "";
		  }
		  
	 }
	 
	 function  deletaFoto() {
		 var preview = document.querySelector('img');
		 preview.src = '';
		 document.getElementById("target").src = '';
		 document.getElementById("base64").value = '';
		 document.getElementById("target").removeAttribute("src");
		 $.Jcrop('#target').destroy();

	 } 