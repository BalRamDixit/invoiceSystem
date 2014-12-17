$(document).ready(function () {

    //select all the a tag with name equal to modal
    $('a[name=modal]').click(function (e) {
        //Cancel the link behavior
        e.preventDefault();

        //Get the A tag
        //var id = $(this).attr('href');

        //modified code starts

        var id = "contact_modal";
        var id2 = "#boxes .window";
        var id3 = "#boxes #contact_modal iframe";

        var url = $(this).attr('href');
        var window_rel = $(this).attr('rel');
        var window_rel = window_rel.split('-');

        var window_width = window_rel[0]+'px';
        var window_height = window_rel[1]+'px';

        document.getElementById(id).innerHTML = "<iframe src='" + url + "'></iframe>";

        //modified code ends

        //Get the screen height and width
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();

        //Set heigth and width to mask to fill up the whole screen
        $('#mask').css({ 'width': maskWidth, 'height': maskHeight });

        //$('#'+id).css({ 'width': window_width, 'height': window_height });
        $(id2).css({ 'width': window_width, 'height': window_height });
        $(id3).css({ 'width': window_width, 'height': window_height });

        //transition effect		
        $('#mask').fadeIn(1000);
        $('#mask').fadeTo("slow", 0.8);

        //Get the window height and width
        var winH = $(window).height();
        var winW = $(window).width();

        //Set the popup window to center
        $(id2).css('top', 50);
        $(id2).css('left', winW / 2 - window_rel[0] / 2);
        //transition effect
        $(id2).fadeIn(2000);
    });

    //if close button is clicked
//    $('.window .close').click(function (e) {
//        //Cancel the link behavior
//        e.preventDefault();

//        $('#mask').hide();
//        $('.window').hide();
//    });

    //if mask is clicked
    $('#mask').click(function () {
        $(this).hide();
        $('.window').hide();
    });

    $('#jk').click(function () {
        $('.window').hide();
    });

    //    $(window).resize(function () {

//        var box = $('#boxes .window');

//        //Get the screen height and width
//        var maskHeight = $(document).height();
//        var maskWidth = $(window).width();

//        //Set height and width to mask to fill up the whole screen
//        $('#mask').css({ 'width': maskWidth, 'height': maskHeight });

//        //Get the window height and width
//        var winH = $(window).height();
//        var winW = $(window).width();

//        //Set the popup window to center
//        box.css('top', winH / 2 - box.height() / 2);
//        box.css('left', winW / 2 - box.width() / 2);

//    });

});