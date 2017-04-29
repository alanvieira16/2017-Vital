$(document).ready(function(){
	if($(".field-phone").length){
		$(".field-phone").focusout(function(){
		    var phone, element;
		    element = $(this);
		    element.unmask();
		    phone = element.val().replace(/\D/g, '');
		    if(phone.length > 10) {
		        element.mask("(99) 99999-999?9");
		    } else {
		        element.mask("(99) 9999-9999?9");
		    }
		}).trigger('focusout');
	}
	if($(".field-birthday").length){
		$(".field-birthday").mask("99/99/9999");
	}
});