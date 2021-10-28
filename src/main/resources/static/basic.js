let targetId;

$(document).ready(function () {
    $('#query').on('keypress', function (e) {
        if(e.key == 'Enter') {
            execSearch();
        }
    });
    $('#close').on('click', function (){
        $('#container').removeClass('active');
    })

    $('.nav div.nav-see').on('click', function (){
        $('div.nav-see').addClass('active');
        $('div.nav-search').removeClass('active');

        $('#see-area').show();
        $('#search-area').hide();
    })

    $('.nav div.nav-search').on('click', function (){
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

function execSearch() {
    /**
     * 검색어 input id : query
     * 검색결과 목록 : #search-result-box
     * 검색 결과 HTML 만드는 함수 : addHTML()
     */

    let query = $('#query').val();

    if (query == '') {
        alert('검색어를 입력해주세요');
        $('query').focus();
        return;
    }

    $.ajax({
        type : 'GET',
        url : `/api/search?query=${query}`,
        success : function (response) {
            $('#search-result-box').empty();

            for (let i = 0; i < response.length; i++) {
                let itemDto = response[i];
                let tempHtml = addHTML(itemDto);
                $('#search-result-box').append(tempHtml);
            }
        }
    })

}

function addHTML (itemDto) {
    return `<div class="search-itemDto"> 
           <div class="search-itemDto-left"> 
               <img src="${itemDto.image}" alt="">
        </div>
        <div class="search-itemDto-center">
           <div>${itemDto.title}</div>
           <div class="price">
               ${numberWithCommas(itemDto.lprice)}
               <span class="unit">원</span>
           </div>
        </div>
        <div class="search-itemDto-right">
           <img src="/images/icon-save.png" alt="" onclick="addProduct(${JSON.stringify(itemDto)})">
        </div>
        </div>`
}

function addProduct (itemDto) {
    /**
     * Modal창 만들기
     *  - $('#container').addClass('active');
     *  - data를 ajax로 전달할때는 두가지가 중요함
     *      1. contentType: 'application/json',
     *      2. data: JSON.stringify(itemDto),
     */

    //1. POST /api/products 에 관심 상품 생성 요청

    $.ajax ({
        type: 'POST',
        url: '/api/products',
        contentType : "application/json",
        data: JSON.stringify(itemDto),
        success: function (response) {
            //2. 응답 함수에서 modal을 뜨게 하고, targetId를 response.id로 설정
            $('#container').addClass('active');
            targetId = response.id;
        }
    })
}

function showProduct () {
    /**
     * 관심상품 목록 : #product-container
     * 검색결과 목록 : #search-result-box
     * 관심상품 HTML 만들기 : addProductItem()
     */

    $.ajax({
        type: 'GET',
        url: '/api/products',
        success : function (response) {
            $('#product-container').empty();
            $('#search-result-box').empty();

            for (let i = 0; i < response.length; i++) {
                let product = response[i];
                let tempHtml = addProductItem(product);
                $('#product-container').append(tempHtml);
            }
        }
    })
}

function addProductItem (product) {
    return `<div class="product-card" onclick="window.location.href='${product.link}'">
                <div class="card-header">
                    <img src="${product.image}" alt="">
                </div>
                <div class="card-body">
                    <div class="title">
                        ${product.title}
                    </div>
                    <div class="lprice">
                        <span>${numberWithCommas(product.lprice)}</span>원
                    </div>
                    <div class="isgood ${product.lprice > product.myprice ? 'none' : ''}">
                        최저가
                    </div>
                </div>
            </div>`;
}

function setMyPrice() {}