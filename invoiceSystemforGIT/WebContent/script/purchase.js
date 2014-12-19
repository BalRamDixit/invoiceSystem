// JavaScript Document
function p_create() {
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			subbody.innerHTML = "<div id='subbody1'></div><div id='subbody2'></div>";
			subbody1.innerHTML = value;
			subbody2.innerHTML = "";
			datechange1();
			datechange2();
			get_vendors();
			get_clients();
		}
	}
	ajaxob.open("POST", "pages/purchase/newpurchaseorder.html", true);
	ajaxob.send();
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
			for (i = 0; i < obj.length; i++) {
				var option = document.createElement("option");
				option.text = obj[i].contactperson;
				option.value = obj[i].uuid;
				var select = document.getElementById("vendorid");
				select.appendChild(option);
			}
		}
	}
	ajaxob.open("POST", "pages/purchase/getvendor.jsp", true);
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
			for (i = 0; i < obj.length; i++) {
				var option = document.createElement("option");
				option.text = obj[i].contactperson;
				option.value = obj[i].uuid;
				var select = document.getElementById("clientid");
				select.appendChild(option);
			}
		}
	}
	ajaxob.open("POST", "pages/purchase/getclient.jsp", true);
	ajaxob.send();
}
function p_insert() {
	var vendorid = document.getElementById("vendorid").value;
	var clientid = document.getElementById("clientid").value;
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
	if (vendorid == "") {
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
	} else if (pono == "") {
		alert("Purchased Order Number should not be Blank");
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
		stextra=0+"";
		if(stextra.checked==true){
			stextra=1+"";
		} else {
			stextra=2+"";
		}

		var jsonobject = {
			'vendorid' : vendorid,
			'clientid' : clientid,
			'unittype' : utype,
			'rate' : rate,
			'duration' : duration,
			'sdate' : sdate,
			'edate' : edate,
			'particular' : particular,
			'pterms' : pterms,
			'pono' : pono,
			'postatus' : postatus,
			'pstatus' : pstatus,
			'stextra' : stextra
		};
		var ajaxob;
		if (window.XMLHttpRequest) {
			ajaxob = new XMLHttpRequest();
		}
		ajaxob.onreadystatechange = function() {
			if (ajaxob.readyState == 4 && ajaxob.status == 200) {
				value = ajaxob.responseText;
				p_view();
			}
		}
		ajaxob.open("POST", "pages/purchase/insertpurchaseorder.jsp?data="
				+ JSON.stringify(jsonobject), true);
		ajaxob.send();
	}
}
function p_close() {
	p_view();
}
function p_view() {
	subbody.innerHTML = "<div id='subbody1'></div><div id='subbody2'></div><div id='subbody3'></div><div data-role='popup' id='subbody4'></div>";
	$('#subbody1').jtable({
		title : 'Table of people',
		paging : true,
		pageSize : 10,
		sorting : true,
		defaultSorting : 'createdate ASC',
		actions : {
			listAction : 'pages/purchase/purchaseinfo.jsp?action=list',
			updateAction : 'pages/purchase/purchaseinfo.jsp?action=update',
			deleteAction : 'pages/purchase/purchaseinfo.jsp?action=delete'
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
				edit : true
			},
			purchaseorderno : {
				title : 'Order NO',
				width : '10%',
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
	subbody2.innerHTML = "<div style='text-align:right;background-color:#fff;border-left-color-value:#c8c8c8'><a href='#' style='text-decoration:none;' onclick='p_create()'>+ Add New Record</a></div>";
	subbody3.innerHTML = "<a href='pages/pdf/purchaseorderlist.jsp' target='_blank'><button> Export to PDF</button></a>";
}
function getdate() {
	var d = new Date();
	todaydate.innerHTML = d.getDate() + " - " + (d.getMonth() + 1) + " - "
			+ d.getFullYear();
}
function fun() {
	var a = document.getElementById("rate").value;
	var b = document.getElementById("duration").value;
	if (a != "" && b != "") {
		var c = parseFloat(a) * parseFloat(b);
		ans.innerHTML = c.toFixed(2) + " Rs.";
	} else {
		ans.innerHTML = "";
	}
}
function getParticular(uuid)
{
	var ajaxob;
	if (window.XMLHttpRequest) {
		ajaxob = new XMLHttpRequest();
	}
	ajaxob.onreadystatechange = function() {
		if (ajaxob.readyState == 4 && ajaxob.status == 200) {
			value = ajaxob.responseText;
			subbody4.innerHTML=value;
			
		}
	}
	ajaxob.open("POST", "pages/purchase/getParticular.jsp?uuid="
			+ uuid, true);
	ajaxob.send();
}