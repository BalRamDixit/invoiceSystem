// JavaScript Document
function i_view() {
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			subbody.innerHTML = "<div id='subbody1'></div><div id='subbody2'></div>";
			subbody1.innerHTML = value;
			getdate();
			datechange1();
			getpono();
		}
	}
	ajaxob.open("POST", "pages/invoice/printinvoice.html", true);
	ajaxob.send();
}
function getpono() {
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			var obj = JSON.parse(value);
			for (i = 0; i < obj.length; i++) {
				var option = document.createElement("option");
				option.value = obj[i].purchaseorderno;
				var select = document.getElementById("ponolist");
				select.appendChild(option);
			}
		}
	}
	ajaxob.open("POST", "pages/invoice/getpono.jsp", true);
	ajaxob.send();
}
function getdate() {
	var d = new Date();
	document.getElementById("sdate").value = d.getFullYear() + "-"
			+ (d.getMonth() + 1) + "-" + d.getDate();
}
val1 = "0";
val2 = "0";
val3 = "0";
val4 = "0";
val5 = "0";
function getdetail() {
	var pono = document.getElementById("pono").value;
	if (pono == "") {
	} else {
		var purchaseorder = {
			"pono" : pono
		};
		var ajaxob;
		if (window.XMLHttpRequest) {
			ajaxob = new XMLHttpRequest();
		}
		ajaxob.onreadystatechange = function() {
			if (ajaxob.readyState == 4 && ajaxob.status == 200) {
				value = ajaxob.responseText;
				var obj = JSON.parse(value);
				particular.innerHTML = obj.particular;
				paymentterms.innerHTML = obj.paymentterms;
				var stextra = obj.stextra;
				var tax = 0;
				if (stextra == "1") {
					tax = 0.1236 * parseFloat(obj.total);
					document.getElementById("taxvalue").value = tax.toFixed(2);
					document.getElementById("total").value = (parseFloat(obj.total) + parseFloat(tax))
							.toFixed(2);
				} else {
					xtotal = parseFloat(obj.total) / 1.1236;
					tax = parseFloat(obj.total) - xtotal;
					document.getElementById("taxvalue").value = tax.toFixed(2);
					document.getElementById("total").value = parseFloat(
							obj.total).toFixed(2);
				}
				expensiveClear();
			}
		}
		ajaxob.open("POST", "pages/invoice/getdetailforinvoice.jsp?data="
				+ JSON.stringify(purchaseorder), true);
		ajaxob.send();
	}
}
function expensiveClear()
{
	document.getElementById("exp1").value="";
	document.getElementById("value1").value="";
	document.getElementById("ch1").checked=false;
	val1="0";
	
	document.getElementById("exp2").value="";
	document.getElementById("value2").value="";
	document.getElementById("ch2").checked=false;
	val2="0";
	
	document.getElementById("exp3").value="";
	document.getElementById("value3").value="";
	document.getElementById("ch3").checked=false;
	val3="0";
	
	document.getElementById("exp4").value="";
	document.getElementById("value4").value="";
	document.getElementById("ch4").checked=false;
	val4="0";
	
	document.getElementById("exp5").value="";
	document.getElementById("value5").value="";
	document.getElementById("ch5").checked=false;
	val5="0";
}
function insertinvoice() {
	var invoiceno = document.getElementById("invoiceno").value;
	var pono = document.getElementById("pono").value;
	var sdate = document.getElementById("sdate").value;
	var exp1 = document.getElementById("exp1").value;
	var value1 = document.getElementById("value1").value;
	var ch1 = document.getElementById("ch1");
	var exp2 = document.getElementById("exp2").value;
	var value2 = document.getElementById("value2").value;
	var ch2 = document.getElementById("ch2");
	var exp3 = document.getElementById("exp3").value;
	var value3 = document.getElementById("value3").value;
	var ch3 = document.getElementById("ch3");
	var exp4 = document.getElementById("exp4").value;
	var value4 = document.getElementById("value4").value;
	var ch4 = document.getElementById("ch4");
	var exp5 = document.getElementById("exp5").value;
	var value5 = document.getElementById("value5").value;
	var ch5 = document.getElementById("ch5");
	var taxvalue = document.getElementById("taxvalue").value;
	var total = document.getElementById("total").value;
	if (invoiceno == "") {
		alert("insert invoice first");
	} else if (pono == "") {
		alert("select purchase order number");
	} else if (sdate == "") {
		alert("Select A Invoice date");
	} else if (ch1.checked == true) {
		if (exp1 == "" || value1 == "") {
			alert("provide all valid values for expensive 1");
		}
	} else if (exp1 != "" && value1 == "" || exp1 == "" && value1 != "") {
		alert("provide all valid values for expensive 1");
	} else if (ch2.checked == true) {
		if (exp2 == "" || value2 == "") {
			alert("provide all valid values for expensive 2");
		}
	} else if (exp2 != "" && value2 == "" || exp2 == "" && value2 != "") {
		alert("provide all valid values for expensive 2");
	} else if (ch3.checked == true) {
		if (exp3 == "" || value3 == "") {
			alert("provide all valid values for expensive 3");
		}
	} else if (exp3 != "" && value3 == "" || exp3 == "" && value3 != "") {
		alert("provide all valid values for expensive 3");
	} else if (ch4.checked == true) {
		if (exp4 == "" || value4 == "") {
			alert("provide all valid values for expensive 4");
		}
	} else if (exp4 != "" && value4 == "" || exp4 == "" && value4 != "") {
		alert("provide all valid values for expensive 4");
	} else if (ch5.checked == true) {
		if (exp5 == "" || value5 == "") {
			alert("provide all valid values for expensive 5");
		}
	} else if (exp5 != "" && value5 == "" || exp5 == "" && value5 != "") {
		alert("provide all valid values for expensive 5");
	} else if (taxvalue == "") {
		alert("TaxValue is not Valid");
	} else if (total == "") {
		alert("provide valid total");
	} else {

	}
}
function updateTotal(x) {
	var value1 = document.getElementById("value1").value;
	var value2 = document.getElementById("value2").value;
	var value3 = document.getElementById("value3").value;
	var value4 = document.getElementById("value4").value;
	var value5 = document.getElementById("value5").value;
	if (x == 1 && value1 != "") {
		if (parseInt(val1) == 0) {
			val1 = parseFloat(value1);
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value) + parseFloat(value1))
					.toFixed(2);
		} else {
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value)
					+ parseFloat(value1) - parseFloat(val1)).toFixed(2);
			val1 = parseFloat(value1);
		}
	}
	else if(x==1 && parseInt(val1)>0 ){
		document.getElementById("total").value = (parseFloat(document
				.getElementById("total").value)
				- parseFloat(val1)).toFixed(2);
		val1 = "0";
	}
	if (x == 2 && value2 != "") {
		if (parseInt(val2) == 0) {
			val2 = parseFloat(value2);
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value) + parseFloat(value2))
					.toFixed(2);
		} else {
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value)
					+ parseFloat(value2) - parseFloat(val2)).toFixed(2);
			val2 = parseFloat(value2);
		}
	}
	else if(x==2 && parseInt(val2)>0 ){
		document.getElementById("total").value = (parseFloat(document
				.getElementById("total").value)
				 - parseFloat(val2)).toFixed(2);
		val2 = "0";
	}
	if (x == 3 && value3 != "") {
		if (parseInt(val3) == 0) {
			val3 = parseFloat(value3);
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value) + parseFloat(value3))
					.toFixed(2);
		} else {
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value)
					+ parseFloat(value3) - parseFloat(val3)).toFixed(2);
			val3 = parseFloat(value3);
		}
	}
	else if(x==3 && parseInt(val3)>0 ){
		document.getElementById("total").value = (parseFloat(document
				.getElementById("total").value)
				 - parseFloat(val3)).toFixed(2);
		val3 = "0";
	}
	if (x == 4 && value4 != "") {
		if (parseInt(val4) == 0) {
			val4 = parseFloat(value4);
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value) + parseFloat(value4))
					.toFixed(2);
		} else {
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value)
					+ parseFloat(value4) - parseFloat(val4)).toFixed(2);
			val4 = parseFloat(value4);
		}
	}
	else if(x==4 && parseInt(val4)>0 ){
		document.getElementById("total").value = (parseFloat(document
				.getElementById("total").value)
				- parseFloat(val4)).toFixed(2);
		val4 = "0";
	}
	if (x == 5 && value5 != "") {
		if (parseInt(val5) == 0) {
			val5 = parseFloat(value5);
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value) + parseFloat(value5))
					.toFixed(2);
		} else {
			document.getElementById("total").value = (parseFloat(document
					.getElementById("total").value)
					+ parseFloat(value5) - parseFloat(val5)).toFixed(2);
			val5 = parseFloat(value5);
		}
	}
	else if(x==5 && parseInt(val5)>0 ){
		document.getElementById("total").value = (parseFloat(document
				.getElementById("total").value)
				- parseFloat(val5)).toFixed(2);
		val5 = "0";
	}
}