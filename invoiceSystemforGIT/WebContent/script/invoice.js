// JavaScript Document
function i_view()
{
	var ajaxob;
	if(window.XMLHttpRequest)
	{
		ajaxob=new XMLHttpRequest();
	}
	ajaxob.onreadystatechange=function()
	{
		if (ajaxob.readyState == 4 && ajaxob.status == 200)
		{
			value = ajaxob.responseText;
			subbody.innerHTML="<div id='subbody1'></div><div id='subbody2'></div>";
			subbody1.innerHTML=value;
			getdate();
			datechange1();
			getpono();
		}
	}
	ajaxob.open("POST", "pages/invoice/printinvoice.html",true);
	ajaxob.send();
}
function getpono()
{
	var ajaxob;
	if(window.XMLHttpRequest)
	{
		ajaxob=new XMLHttpRequest();
	}
	ajaxob.onreadystatechange=function()
	{
		if (ajaxob.readyState == 4 && ajaxob.status == 200)
		{
			value = ajaxob.responseText;
			var obj = JSON.parse(value);
			for(i=0;i<obj.length;i++)
			{
				var option = document.createElement("option");
				option.value = obj[i].purchaseorderno;
				var select = document.getElementById("ponolist");
				select.appendChild(option);
			}
		}
	}
	ajaxob.open("POST", "pages/invoice/getpono.jsp",true);
	ajaxob.send();
}
function getdate()
{
	var d=new Date();
	document.getElementById("sdate").value=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
}
function getdetail()
{
	var pono=document.getElementById("PONO").value;
	if(pono=="")
	{
	}
	else
	{
		var purchaseorder={"pono":pono};
		var ajaxob;
		if(window.XMLHttpRequest)
		{
			ajaxob=new XMLHttpRequest();
		}
		ajaxob.onreadystatechange=function()
		{
			if (ajaxob.readyState == 4 && ajaxob.status == 200)
			{
				value = ajaxob.responseText;
				var obj = JSON.parse(value);
				document.getElementById("task").value="PArticular -- "+obj.particular+"\nPayment Terms -- "+obj.paymentterms;
				var stextra=obj.stextra;
				var tax=0;
				if(stextra=="1")
				{
					tax=0.1236*parseFloat(obj.total);
					document.getElementById("taxvalue").value=tax.toFixed(2);
					document.getElementById("total").value=(parseFloat(obj.total)+parseFloat(tax)).toFixed(2);
				}
				else
				{
					
					xtotal=parseFloat(obj.total)/1.1236;
					tax=parseFloat(obj.total)-xtotal;
					document.getElementById("taxvalue").value=tax.toFixed(2);
					document.getElementById("total").value=parseFloat(obj.total).toFixed(2);
				}
				
			}
		}
		ajaxob.open("POST", "pages/invoice/getdetailforinvoice.jsp?data="+JSON.stringify(purchaseorder),true);
		ajaxob.send();
	}
}
function insertinvoice()
{
	var invoiceno=document.getElementById("invoiceno").value;
	var pono=document.getElementById("pono").value;
	var sdate=document.getElementById("sdate").value;
	var task=document.getElementById("task").value;
	var exp1=document.getElementById("exp1").value;
	var value1=document.getElementById("value1").value;
	var ch1=document.getElementById("ch1");
	var exp2=document.getElementById("exp2").value;
	var value2=document.getElementById("value2").value;
	var ch2=document.getElementById("ch2");
	var exp3=document.getElementById("exp3").value;
	var value3=document.getElementById("value3").value;
	var ch3=document.getElementById("ch3");
	var exp4=document.getElementById("exp4").value;
	var value4=document.getElementById("value4").value;
	var ch4=document.getElementById("ch4");
	var exp5=document.getElementById("exp5").value;
	var value5=document.getElementById("value5").value;
	var ch5=document.getElementById("ch5");
	var taxvalue=document.getElementById("taxvalue").value;
	var total=document.getElementById("total").value;
	if(invoiceno=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
	else if(pono=="")
	{
		
	}
}