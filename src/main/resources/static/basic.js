let targetId;

$(document).ready(function () {
    $('#query').on('keypress', function (e) {
        if(e.key == 'Enter') {
            execSearch();
        }
    });
    $('#close').on('click', function (e){
        $('#container').removeClass('active');
    })

    $('.nav div.nav-see').on('click', function (e){
        $('div.nav-see').addClass('active');
        $('div.nav-search').removeClass('active')

        $('#see-area').show();
        $('#search-area').hide();
    })

    $('.nav div.nav-search').on('click', function (e){
        $('div.nav-see').removeClass('active');
        $('div.nav-search').addClass('active');

        $('#see-area').hide();
        $('#search-area').show();
    })

    $('#see-area').show();
    $('#search-area').hide();

    showProduct();
})

function numberWithCommas(x){
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function execSearch() {}

function addHTML (itemDto) {}

function addProduct (itemDto) {}

function showProduct () {}

function addProductItem (product) {}

function setMyPrice() {}