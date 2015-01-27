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
			subbody2.innerHTML = "<a href='PurchaseOrderPdf' target='_blank'><button>Export to PDF</button></a>";
			getdate();
			datechange1();
			getpono();
			totalexp=5;
		}
	}
	ajaxob.open("GET", "pages/invoice/printinvoice.html", true);
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
	ajaxob.open("POST", "PurchaseOrderNo", true);
	ajaxob.send();
}
function getdate() {
	var d = new Date();
	var d = new Date();
	var f1=d.getDate();
	if(parseInt(d.getDate())<10)
	{
		f1="0"+d.getDate();
	}
	var f2=d.getMonth() + 1;
	if(parseInt(d.getMonth() + 1)<10)
	{
		f2="0"+(d.getMonth() + 1);
	}
	document.getElementById("sdate").value =  f1+ "-" + f2+ "-"
			+ d.getFullYear();
	
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
				$('.currency').text(obj.currency);
				if(stextra=="1")
				{
					stax.innerHTML=" NOT INCLUDED";
				}
				else
				{
					stax.innerHTML=" INCLUDED";
				}
				addExpenseType();
				addExpense();
			}
		}
		ajaxob.open("POST", "DetailForInvoiceInfo?data="
				+ JSON.stringify(purchaseorder), true);
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
	ajaxob.open("POST", "UpdateInvoice", true);
	ajaxob.send();
}
function addExpense()
{
	expenses.innerHTML="<div id=\"expense_list\"></div>";
	$('#expense_list').jtable({
		paging: true,
		pageSize: 3,
		sorting: true,
		defaultSorting: 'exno ASC',
		actions: {
			listAction: 'ExpenseInfo?action=list',
			updateAction: 'ExpenseInfo?action=update',
			deleteAction: 'ExpenseInfo?action=delete'
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
	var exp =document.getElementById("exp").value;
	var cost=document.getElementById("value").value;
	var inc =document.getElementById("inclusive").value;
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
			(document.getElementById("inclusive")).selectedIndex=0;
			$("#dialog").bPopup().close();
		}
	}
	ajaxob.open("POST", "InsertExpense?data="
			+ JSON.stringify(jsonobject), true);
	ajaxob.send();
}
function addExpenseType()
{
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			var obj = JSON.parse(value);
			var select = document.getElementById("etype");
			select.innerHTML = "<option value=''>Select Expense Type</option>";
			for (i = 0; i < obj.length; i++) {
				var option = document.createElement("option");
				option.text = obj[i].exptype;
				option.value = obj[i].exptype;
				select.appendChild(option);
			}
		}
	}
	ajaxob.open("POST", "ExpenseType", true);
	ajaxob.send();
}
function openAddExpenseWindow()
{
	$("#dialog").bPopup();
}