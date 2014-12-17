function v_view()
{
			subbody.innerHTML="<div id='subbody1'></div><div id='subbody2'></div>";
			$('#subbody1').jtable({
				title: 'Table of people',
				paging: true,
				pageSize: 10,
				sorting: true,
				defaultSorting: 'contactperson ASC',
				actions: {
					listAction: 'pages/vendor/vendorinfo.jsp?action=list',
					createAction: 'pages/vendor/vendorinfo.jsp?action=create',
					updateAction: 'pages/vendor/vendorinfo.jsp?action=update',
					deleteAction: 'pages/vendor/vendorinfo.jsp?action=delete'
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
			subbody2.innerHTML="<a href='pages/pdf/vendorlist.jsp' target='_blank'><button> Export to PDF</button></a>";
}
function export_vendor()
{
	window.open('pages/pdf/vendorlist.php','ExamHOME','fullscreen=yes','resizable=yes,scrollbars=yes');
}