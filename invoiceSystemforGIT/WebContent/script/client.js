function basic()
{
	subbody.innerHTML="";
}

function c_view()
{
			subbody.innerHTML="<div id='subbody1'></div><div id='subbody2'></div>";
			$('#subbody1').jtable({
				title: 'Table of people',
				paging: true,
				pageSize: 10,
				sorting: true,
				defaultSorting: 'contactperson ASC',
				actions: {
					listAction: 'pages/client/clientinfo.jsp?action=list',
					createAction: 'pages/client/clientinfo.jsp?action=create',
					updateAction: 'pages/client/clientinfo.jsp?action=update',
					deleteAction: 'pages/client/clientinfo.jsp?action=delete'
				},
				fields: {
					uuid: {
						key: true,
						create: false,
						edit: false,
						list: false
					},
					contactperson: {
						title: 'contact person',
						width: '20%',
						create: true,
						edit: true
					},
					company: {
						title: 'company',
						width: '20%',
						create: true,
						edit: true
					},
					contactno: {
						title: 'contactno',
						width: '10%',
						create: true,
						edit: true
					},
					email: {
						title: 'email',
						width: '10%',
						create: true,
						edit: true
					},
					city: {
						title: 'city',
						width: '10%',
						create: true,
						edit: true
					},
					address: {
						title: 'address',
						width: '20%',
						create: true,
						edit: true
					},
					url: {
						title: 'url',
						width: '10%',
						create: true,
						edit: true
					}
				}
			});

			//Load person list from server
			$('#subbody1').jtable('load');
			subbody2.innerHTML="<a href='pages/pdf/clientlist.jsp' target='_blank'><button> Export to PDF</button></a>";
}
function export_client()
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
			
		}
	}
	ajaxob.open("POST", "pages/pdf/clientlist.jsp",true);
	ajaxob.send();
	
}