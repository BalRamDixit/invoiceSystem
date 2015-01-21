function insertinvoice() {
	var invoiceno = document.getElementById("invoiceno").value;
	var sdate     =     document.getElementById("sdate").value;
	var taxvalue  =  document.getElementById("taxvalue").value;
	var total     =     document.getElementById("total").value;
	var status="2";
	if (invoiceno == "") {
		status="1";
		alert("insert invoice first");
	} else if (sdate == "") {
		status="1";
		alert("Select A Invoice date");
	} else if (taxvalue == "") {
		status="1";
		alert("TaxValue is not Valid");
	} else if (total == "") {
		status="1";
		alert("provide valid total");
	} 
	if(status=="2") {
		var jsonobject = {
			'invoiceno' : invoiceno,
			'sdate' : sdate,
			'taxvalue' : taxvalue,
			'total' : total,
		};
		var ajaxob;
		if (window.XMLHttpRequest) {
			ajaxob = new XMLHttpRequest();
		}
		ajaxob.onreadystatechange = function() {
			if (ajaxob.readyState == 4 && ajaxob.status == 200) {
				value=ajaxob.responseText;
				previewInvoice(value);
			}
		}
		ajaxob.open("GET", "InvoicePreviewInsert?data="
				+ JSON.stringify(jsonobject), true);
		ajaxob.send();
	}
}
function previewInvoice(valuex)
{
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			subbody.innerHTML = "<div id='subbody1'></div><div id='subbody2'></div>";
			subbody1.innerHTML = value;
			addExpense1();
			updateinvoicepreview(valuex);
			updateComment();
		}
	}
	ajaxob.open("GET", "pages/invoice/invoicepreview.html", true);
	ajaxob.send();
}
function addExpense1()
{
	document.getElementById("expense-table").innerHTML="<div id=\"expense_list\"></div>";
	$('#expense_list').jtable({
		paging: true,
		pageSize: 1000,
		sorting: true,
		defaultSorting: 'exno ASC',
		actions: {
			listAction: 'ExpenseInfo?action=list',
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
				width: '50%',
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
function updateinvoicepreview(value)
{
	var obj = JSON.parse(value);
	document.getElementById("date").value=obj.sdate;
	document.getElementById("invoiceno").value=obj.invoiceno;
	document.getElementById("tax").value=obj.tax;
	document.getElementById("total").value=obj.total;
	$('.currency').text(obj.currency);
	document.getElementById("pototal").value=obj.pototal;
}
function saveandprintInvoice()
{
	
}
function addComments()
{
	$("#commentpopup").bPopup();
}
function insertComment()
{
	var com=document.getElementById("commentsave").value;
	$("#commentpopup").bPopup().close();
	if(com=="")
	{
		alert("Comment should no be blank");
	}
	else
	{
		var jsonobject = {
				"comment":com,
			};
		var ajaxob;
		if (window.XMLHttpRequest) {
			ajaxob = new XMLHttpRequest();
		}
		ajaxob.onreadystatechange = function() {
			if (ajaxob.readyState == 4 && ajaxob.status == 200) {
				updateComment();
			}
		}
		ajaxob.open("GET", "CommentsInfo?action=insert&data="
				+ JSON.stringify(jsonobject), true);
		ajaxob.send();
	}
}
function updateComment()
{
	comment.innerHTML="<div id='comments'></div>";
	$('#comments').jtable({
		paging: true,
		pageSize: 3,
		sorting: true,
		defaultSorting: 'exno ASC',
		actions: {
			listAction: 'CommentsInfo?action=list',
			updateAction: 'CommentsInfo?action=update',
			deleteAction: 'CommentsInfo?action=delete'
		},
		fields: {
			cno: {
				key: true,
				create: false,
				edit: false,
				list: false
			},
			sno: {
				title: 'S NO',
				width: '10%',
				create: true,
				edit: true
			},
			comment: {
				title: 'Comments',
				width: '80%',
				create: true,
				edit: true
			}
		}
	});
	//Load person list from server
	$('#comments').jtable('load');
}