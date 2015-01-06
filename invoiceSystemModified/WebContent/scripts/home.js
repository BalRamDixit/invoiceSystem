// JavaScript Document
function auth()
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
			body.innerHTML=value;
		}
	}
	ajaxob.open("post", "pages/home.html",true);
	ajaxob.send();
}
function validate(text,control)
{
	$(document).ready(function()
	{
  		$(control).click(function()
		{
    		$(this).show();
  		});
	});
}