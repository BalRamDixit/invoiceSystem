// JavaScript Document
var showbpopup = 0;
function p_create() {
	getNewPurchaseOrder();

}
function get_vendors() {
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			var obj = JSON.parse(value);
			var select = document.getElementById("vendorid");
			select.innerHTML = "<option value=''>Select Vendor</option>";
			for (i = 0; i < obj.length; i++) {
				var option = document.createElement("option");
				option.text = obj[i].contactperson;
				option.value = obj[i].uuid;
				select.appendChild(option);
			}
		}
	}
	ajaxob.open("POST", "VendorList", true);
	ajaxob.send();
}
function get_clients() {
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			var obj = JSON.parse(value);
			var select = document.getElementById("clientid");
			select.innerHTML = "<option value=''>Select Client</option>";
			for (i = 0; i < obj.length; i++) {
				var option = document.createElement("option");
				option.text = obj[i].contactperson;
				option.value = obj[i].uuid;
				select.appendChild(option);
			}
		}
	}
	ajaxob.open("POST", "ClientList", true);
	ajaxob.send();
}
function p_insert() {
	var vendorid = document.getElementById("vendorid").value;
	var clientid = document.getElementById("clientid").value;
	var cdate = document.getElementById("cdate").value;
	var sdate = document.getElementById("sdate").value;
	var edate = document.getElementById("edate").value;
	var duration = document.getElementById("duration").value;
	var rate = document.getElementById("rate").value;
	var particular = document.getElementById("particular").value;
	var pterms = document.getElementById("pterms").value;
	var pono = document.getElementById("pono").value;
	var postatus = document.getElementById("postatus").value;
	var pstatus = document.getElementById("pstatus").value;
	var unit1 = document.getElementById("unit1");
	var unit2 = document.getElementById("unit2");
	var stextra = document.getElementById("stextra");
	var currency = document.getElementById("currency").value;
	if (pono == "") {
		alert("Purchased Order Number should not be Blank");
	} else if (cdate == "") {
		alert("Current date Should not be blank");
	} else if (vendorid == "") {
		alert("Select A vendor");
	} else if (clientid == "") {
		alert("select a client");
	} else if (unit1.checked == false && unit2.checked == false) {
		alert("Please Select A unit Type");
	} else if (rate == "") {
		alert("rate should not be Blank");
	} else if (duration == "") {
		alert("duration should not be Blank");
	} else if (sdate == "") {
		alert("start date should not be Blank");
	} else if (edate == "") {
		alert("End date should not be Blank");
	} else if (particular == "") {
		alert("particular should not be Blank");
	} else if (pterms == "") {
		alert("Payment Terms should not be Blank");
	} else if (postatus == "") {
		alert("Purchased Order Status should not be Blank");
	} else if (pstatus == "") {
		alert("Payment Status should not be Blank");
	} else {
		utype = 0 + "";
		if (unit1.checked == true) {
			utype = 1 + "";
		} else {
			utype = 2 + "";
		}
		st = 0 + "";
		if (stextra.checked == true) {
			st = 1 + "";
		} else {
			st = 2 + "";
		}

		var jsonobject = {
			'vendorid' : vendorid,
			'clientid' : clientid,
			'unittype' : utype,
			'rate' : rate,
			'duration' : duration,
			'cdate' : cdate,
			'sdate' : sdate,
			'edate' : edate,
			'particular' : particular,
			'pterms' : pterms,
			'pono' : pono,
			'postatus' : postatus,
			'pstatus' : pstatus,
			'currency' : currency,
			'stextra' : st
		};
		var ajaxob;
		if (window.XMLHttpRequest) {
			ajaxob = new XMLHttpRequest();
		}
		ajaxob.onreadystatechange = function() {
			if (ajaxob.readyState == 4 && ajaxob.status == 200) {
				value = ajaxob.responseText;
				pCloseAfterSave();
			}
		}
		ajaxob.open("POST", "InsertPurchaseOrder?data="
				+ JSON.stringify(jsonobject), true);
		ajaxob.send();
	}
}
function p_close() {
	$("#porder").bPopup().close();
	$("#porder").remove();
	porder1.innerHTML = "<div id='porder' class='popup'></div>";
}
function pCloseAfterSave() {
	$("#porder").bPopup().close();
	$("#porder").remove();
	p_view();
}
function p_view() {
	subbody.innerHTML = "<div id='subbody1'></div><div id='subbody2'></div><div id='subbody3'></div><div id='subbody4' class='popup'></div><div id='porder1'></div>";
	$('#subbody1').jtable({
		title : 'Purchase Orders',
		paging : true,
		pageSize : 10,
		sorting : true,
		defaultSorting : 'createdate ASC',
		actions : {
			listAction : 'PurchaseOrderInfo?action=list',
			updateAction : 'PurchaseOrderInfo?action=update',
			deleteAction : 'PurchaseOrderInfo?action=delete'
		},
		fields : {
			uuid : {
				key : true,
				create : false,
				edit : false,
				list : false
			},
			vendorname : {
				title : 'vendor',
				width : '10%',
				create : false,
				edit : false
			},
			clientname : {
				title : 'client',
				width : '10%',
				create : false,
				edit : false
			},
			createdate : {
				title : 'createdate',
				width : '5%',
				create : false,
				edit : true
			},
			xunit : {
				title : 'unit',
				width : '5%',
				create : true,
				edit : true
			},
			rate : {
				title : 'rate',
				width : '5%',
				create : true,
				edit : true
			},
			duration : {
				title : 'duration',
				width : '5%',
				create : true,
				edit : true
			},
			startdate : {
				title : 'startdate',
				width : '5%',
				create : true,
				edit : true
			},
			enddate : {
				title : 'enddate',
				width : '5%',
				create : true,
				edit : true
			},
			particular : {
				title : 'particular',
				width : '5%',
				create : true,
				edit : false
			},
			purchaseorderno : {
				title : 'Order NO',
				width : '20%',
				create : true,
				edit : true
			},
			paymentstatus : {
				title : 'Payment',
				width : '5%',
				create : true,
				edit : true
			}
		}
	});
	// Load person list from server
	$('#subbody1').jtable('load');
	subbody2.innerHTML = "<div style='text-align:right;background-color:#fff;border-left-color-value:#c8c8c8'><a href='#' style='text-decoration:none;' onclick='p_create()'> Add New Record</a></div>";
	subbody3.innerHTML = "<a href='PurchaseOrderListPdf' target='_blank'><button> Export to PDF</button></a>";
}
function getcdate() {
	var d = new Date();
	var f1 = d.getDate();
	if (parseInt(d.getDate()) < 10) {
		f1 = "0" + d.getDate();
	}
	var f2 = d.getMonth() + 1;
	if (parseInt(d.getMonth() + 1) < 10) {
		f2 = "0" + (d.getMonth() + 1);
	}
	document.getElementById("cdate").value = f1 + "-" + f2 + "-"
			+ d.getFullYear();
}
function fun(x) {
	var a = document.getElementById("rate").value;
	var b = document.getElementById("duration").value;
	if (a != "" && b != "") {
		var c = parseFloat(a) * parseFloat(b);
		ans.innerHTML = c.toFixed(2);
	} else {
		ans.innerHTML = "";
	}
	if (x == 1) {
		unit.innerHTML = "Days";
	} else if (x == 2) {
		unit.innerHTML = "Hours";
	}

}
function getNewPurchaseOrder() {
	porder1.innerHTML = "<div id='porder' class='popup'></div>";
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			porder.innerHTML = value;
			datechange1();
			datechange2();
			datechange3();
			get_vendors();
			get_clients();
			getcdate();
			$("#porder").bPopup({
				modalClose : false,
				opacity : 0.6,
				positionStyle : 'fixed', // 'fixed' or 'absolute'
				modalColor : 'greenYellow',
				fadeSpeed : 'slow'
			});
		}
	}
	ajaxob.open("GET", "pages/purchase/newpurchaseorder.html", true);
	ajaxob.send();
}
function getParticular(uuid) {
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			subbody4.innerHTML = value;
			$("#subbody4").bPopup();
		}
	}
	ajaxob.open("POST", "ParticularInfo?uuid=" + uuid, true);
	ajaxob.send();
}