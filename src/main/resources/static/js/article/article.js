/**
 * 
 */

tinymce.init({
	selector: 'textarea#default'
});

const btnCancel = document.querySelector("#btn-cancel");
btnCancel.addEventListener("click", customAlert);

/*const btnSave = document.querySelector("#btn-save");
btnSave.addEventListener("click", submitForm);

function submitForm(){
	//const articleForm = document.querySelector("#article-form");
	document.forms["article-form"].submit();

}*/
function customAlert(){
	swal({
		title: "you are about to discard the changes!",
		text: "Entered information will be missed! \n Are you sure?",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				swal("Poof! Your imaginary file has been deleted!", {
					icon: "success",
				});
			} else {
				swal("Your imaginary file is safe!");
			}
		});
}
function readURL(input) {
	if (input.files && input.files[0]) {

		var reader = new FileReader();

		reader.onload = function(e) {
			$('.image-upload-wrap').hide();

			$('.file-upload-image').attr('src', e.target.result);
			$('.file-upload-content').show();

			$('.image-title').html(input.files[0].name);
		};

		reader.readAsDataURL(input.files[0]);

	} else {
		removeUpload();
	}
}

function removeUpload() {
	$('.file-upload-input').replaceWith($('.file-upload-input').clone());
	$('.file-upload-content').hide();
	$('.image-upload-wrap').show();
}
$('.image-upload-wrap').bind('dragover', function() {
	$('.image-upload-wrap').addClass('image-dropping');
});
$('.image-upload-wrap').bind('dragleave', function() {
	$('.image-upload-wrap').removeClass('image-dropping');
});
