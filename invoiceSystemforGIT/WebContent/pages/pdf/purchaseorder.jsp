<?php
require_once("util/tcpdf_include.php");

// create new PDF document
$pdf = new TCPDF(PDF_PAGE_ORIENTATION, PDF_UNIT, PDF_PAGE_FORMAT, true, 'UTF-8', false);

// set document information
$pdf->SetCreator('Amanpreet Singh Maini');
$pdf->SetAuthor('AmanPreet Singh Maini');
$pdf->SetTitle('Vendors Information');
$pdf->SetSubject('VEndors');
$pdf->SetKeywords('TCPDF, PDF, example, test, guide');

// set default header data
$pdf->SetHeaderData(PDF_HEADER_LOGO, PDF_HEADER_LOGO_WIDTH, '', '');

// set header and footer fonts
$pdf->setHeaderFont(Array(PDF_FONT_NAME_MAIN, '', PDF_FONT_SIZE_MAIN));
$pdf->setFooterFont(Array(PDF_FONT_NAME_DATA, '', PDF_FONT_SIZE_DATA));

// set default monospaced font
$pdf->SetDefaultMonospacedFont(PDF_FONT_MONOSPACED);

// set margins
$pdf->SetMargins(PDF_MARGIN_LEFT, PDF_MARGIN_TOP, PDF_MARGIN_RIGHT);
$pdf->SetHeaderMargin(PDF_MARGIN_HEADER);
$pdf->SetFooterMargin(PDF_MARGIN_FOOTER);

// set auto page breaks
$pdf->SetAutoPageBreak(TRUE, PDF_MARGIN_BOTTOM);

// set image scale factor
$pdf->setImageScale(PDF_IMAGE_SCALE_RATIO);

// set some language-dependent strings (optional)
if (@file_exists(dirname(__FILE__).'/lang/eng.php')) {
	require_once(dirname(__FILE__).'/lang/eng.php');
	$pdf->setLanguageArray($l);
}

// ---------------------------------------------------------

// set font
$pdf->SetFont('helvetica', 'B', 20);

// add a page
$pdf->AddPage();

$pdf->Write(0, 'INVOICE', '', 0, 'L', true, 0, false, false, 0);

$pdf->SetFont('helvetica', '', 8);

// -----------------------------------------------------------------------------
$curdate=date("j / n / Y");
$tbl = <<<EOD
<table width="70%" height="918" border="0">
  <tr>
    <td width="70%">&nbsp;</td>
    <td width="15%">&nbsp;</td>
    <td width="15%">&nbsp;</td>
  </tr>
  <tr>
    <td height="39">[Company Name]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>[Company Slogan]</td>
    <td colspan="2">Date: &nbsp;&nbsp;&nbsp;$curdate</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">Invoice No [12345]</td>
  </tr>
  <tr>
    <td>[Street Address]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>[city, ST zip]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>Phone: [STD - Ph no]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>Mob: [xxx-xxx-xxxx]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td><p style="background-color:#06F;color:#FFF;font-weight:bold;text-align:center;text-transform:uppercase;width:50%">BILL TO:</p></td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>[Name]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>[Company Name]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>[Street Address]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>[City, ST zip]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>Phone: [STD- Ph no]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>Mob: [xxx-xxx-xxxx]</td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td height="100px" colspan="3">
    	<table width="100%" height="100px" border="1" bordercolor="#333333" style="border-style:solid">
          <tr style="background-color:#06F;color:#FFF;font-weight:bold;text-align:center;text-transform:uppercase">
            <td width="60%">DESCRIPTION</td>
            <td width="10%">unit</td>
            <td width="15%">rate</td>
            <td width="15%">Amount</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td style="background-color:#CCC;color:#000">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td style="background-color:#CCC;color:#000">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td style="background-color:#CCC;color:#000">&nbsp;</td>
          </tr>
        </table>
   	</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>SubTotal</td>
    <td style="background-color:#CCC;color:#000">&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="8">
        <table width="100%" height="210" border="1">
          <tr style="background-color:#06F;color:#FFF;font-weight:bold;text-align:center;text-transform:uppercase">
            <td width="100%">OTHER COMMENTS</td>
          </tr>
          <tr>
            <td height="24">1.TOTAL payment due in 30 days</td>
          </tr>
          <tr>
            <td height="24"> 2.please include the invoice number on your cheque</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </table>
    </td>
    <td>TAX RATE</td>
    <td style="background-color:#CCC;color:#000">&nbsp;</td>
  </tr>
  <tr>
    <td>Tax</td>
    <td style="background-color:#CCC;color:#000">&nbsp;</td>
  </tr>
  <tr>
    <td>OTHER</td>
    <td style="background-color:#CCC;color:#000">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">======================</td>
  </tr>
  <tr>
    <td>TOTAL</td>
    <td style="background-color:#CCC;color:#000">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td height="24" colspan="2">Make your All Cheque payable To</td>
  </tr>
  <tr>
    <td height="24" colspan="2">[Company Name]</td>
  </tr>
  <tr>
    <td colspan="3" style="background-color:#CCC;color:#000;text-align:center">If you have any questions about this invoice,please Contact</td>
  </tr>
  <tr>
    <td colspan="3" style="background-color:#CCC;color:#000;text-align:center">[name,Phone no,E-mail]</td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" style="background-color:#CCC;color:#000;text-align:center">Thank You For Your Business!</td>
  </tr>
</table>
EOD;

$pdf->writeHTML($tbl, true, false, false, false, '');
//Close and output PDF document
$pdf->Output('Invoice_no.pdf', 'I');
?>