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
			subbody2.innerHTML = "<a href='pages/pdf/purchaseorder.jsp' target='_blank'><button>Export to PDF</button></a>";
			getdate();
			datechange1();
			getpono();
			totalexp=5;
			$(function() {
				$( "#dialog" ).dialog({
					autoOpen: false,
					minHeight: 250,
					minWidth: 400
				});
				$( "#opener" ).click(function() {
					$( "#dialog" ).dialog( "open" );
				});
				$( "#closer" ).click(function() {
					$( "#dialog" ).dialog( "close" );
				});
			});
		}
	}
	ajaxob.open("POST", "pages/invoice/printinvoice.html", true);
	ajaxob.send();
}
function getPOlist()
{
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			var obj = JSON.parse(value);
			for (i = 0; i < obj.length; i++) {
				var item = document.createElement("li");
				var link = document.createElement("a");
				link.href="#";
				link.addEventListener("click", fun, false);
				link.text=obj[i].purchaseorderno+"-"+obj[i].particular;
				item.appendChild(link);
				var select = document.getElementById("polist");
				select.appendChild(item);
			}
		}
	}
	ajaxob.open("POST", "pages/invoice/getpolist.jsp", true);
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
				option.text = obj[i].purchaseorderno+"-"+obj[i].paymentterms;
				option.value = obj[i].purchaseorderno;
				var select = document.getElementById("pono");
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
				stextra = obj.stextra;
				var tax = 0;
				document.getElementById("taxvalue").value=Math.round(parseFloat(obj.tax));
				document.getElementById("total").value=Math.round(parseFloat(obj.total));
				document.getElementById("pototal").value=Math.round(parseFloat(obj.pototal));
				document.getElementById("actualpototal").value=Math.round(parseFloat(obj.total));
				if(stextra=="1")
				{
					stax.innerHTML=" NOT INCLUDED";
				}
				else
				{
					stax.innerHTML=" INCLUDED";
				}
				
//				expensiveClear();
				addExpense();
			}
		}
		ajaxob.open("POST", "pages/invoice/getdetailforinvoice.jsp?data="
				+ JSON.stringify(purchaseorder), true);
		ajaxob.send();
	}
}
function insertinvoice() {
	var invoiceno = document.getElementById("invoiceno").value;
	var pono = document.getElementById("pono").value;
	var sdate = document.getElementById("sdate").value;
	var taxvalue = document.getElementById("taxvalue").value;
	var total = document.getElementById("total").value;
	var status="2";
	if (invoiceno == "") {
		status="1";
		alert("insert invoice first");
		
	} else if (pono == "") {
		status="1";
		alert("select purchase order number");
	} else if (sdate == "") {
		status="1";
		alert("Select A Invoice date");
	} else if (taxvalue == "") {
		status="1";
		alert("TaxValue is not Valid");
	} else if (total == "") {
		status="1";
		alert("provide valid total");
	} else  {
		for(var i=1;i<totalexp;i++)
		{
			exp=document.getElementById("exp"+i).value;
			value=document.getElementById("value"+i).value;
			ch=document.getElementById("ch"+i);
			if (ch.checked == true) {
				if (exp == "" || value == "") {
					status="1";
					alert("provide all valid values for expensive 1");
					break;
				}
			} else if (exp != "" && value == "" || exp == "" && value != "") {
				status="1";
				alert("provide all valid values for expensive "+i);
				break;
			}
		}
	}
	if(status=="2") {
		var jsonobject = {
			'invoiceno' : invoiceno,
			'pono' : pono,
			'sdate' : sdate,
			'taxvalue' : taxvalue,
			'total' : total,
			'exp1' : exp1,
			'value1' : value1,
			'ch1' : ch1.checked,
			'exp2' : exp2,
			'value2' : value2,
			'ch2' : ch2.checked,
			'exp3' : exp3,
			'value3' : value3,
			'ch3' : ch3.checked,
			'exp4' : exp4,
			'value4' : value4,
			'ch4' : ch4.checked,
			'exp5' : exp5,
			'value5' : value5,
			'ch5' : ch5.checked
		};
		var ajaxob;
		if (window.XMLHttpRequest) {
			ajaxob = new XMLHttpRequest();
		}
		ajaxob.onreadystatechange = function() {
			if (ajaxob.readyState == 4 && ajaxob.status == 200) {
				value = ajaxob.responseText;
			}
		}
		ajaxob.open("POST", "pages/invoice/putinvoice.jsp?data="
				+ JSON.stringify(jsonobject), true);
		ajaxob.send();
	}
}
function updateInvoice()
{
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			var obj = JSON.parse(value);
			document.getElementById("taxvalue").value=Math.round(parseFloat(obj.tax));
			document.getElementById("total").value=Math.round(parseFloat(obj.total));
			document.getElementById("pototal").value=Math.round(parseFloat(obj.pototal));
		}
	}
	ajaxob.open("POST", "pages/invoice/updateinvoice.jsp", true);
	ajaxob.send();
}
function addExpense()
{
	expenses.innerHTML="<div id=\"expense_list\">";
	$('#expense_list').jtable({
		title: 'Table of expenses',
		paging: true,
		pageSize: 3,
		sorting: true,
		defaultSorting: 'exno ASC',
		actions: {
			listAction: 'pages/invoice/insertexpense.jsp?action=list',
			updateAction: 'pages/invoice/insertexpense.jsp?action=update',
			deleteAction: 'pages/invoice/insertexpense.jsp?action=delete'
		},
		fields: {
			exno: {
				key: true,
				create: false,
				edit: false,
				list: false
			},
			exp: {
				title: 'Other Expenses',
				width: '20%',
				create: true,
				edit: true
			},
			cost: {
				title: 'cost',
				width: '20%',
				create: true,
				edit: true
			},
			Inclusive: {
				title: 'inclusive',
				width: '10%',
				create: true,
				edit: true
			}
		}
	});
	//Load person list from server
	$('#expense_list').jtable('load');
}
function insertExpense()
{
	var exp=document.getElementById("exp").value;
	var cost=document.getElementById("value").value;
	var inc=document.getElementById("inclusive").value;
	var jsonobject = {
			"exp":exp,
			"val":cost,
			"inc":inc
		};
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			updateInvoice();
			addExpense();
			document.getElementById("exp").value="";
			document.getElementById("value").value="";
			document.getElementById("inclusive").value="";
			$( "#dialog" ).dialog( "close" );
		}
	}
	ajaxob.open("POST", "pages/invoice/putexpense.jsp?data="
			+ JSON.stringify(jsonobject), true);
	ajaxob.send();
}