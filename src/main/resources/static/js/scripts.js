$(".answer-write input[type=submit]").click(addAnswer);

$(document).ready(function(){

	$('#numberTable').find('tr').each(function(index) {
	var objId = $(this);
	$(objId).find(".txtNo1").blur(function() {
		var id = eval($(objId).find(this).attr('id').replace('txtNo1_0', ''));
		var check = false;
		var obj = $(objId).find(this);

		if (obj.val() == "") {
			return;
		} else if (isNaN($(objId).find(this).val())) {
			alert("숫자만 입력 가능합니다.");
			obj.val("");
			obj.focus();
			return;
		} else if (obj.val() < 1 || obj.val() > 45) {
			alert("숫자는 1 ~ 45 사이의 숫자만 입력 가능합니다.");
			obj.val("");
			obj.focus();
			return;
		} else {
			$(objId).find('.txtNo1').each(function(index) {
				if (id != index && obj.val() == $(objId).find(this).val()) {
					check = true;
					return false;
				}
			});
			if (check) {
				alert("중복된 값은 입력하실 수 없습니다.");
				obj.val("");
				obj.focus();
				return;
			}
		}
	});
	});
});


String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};