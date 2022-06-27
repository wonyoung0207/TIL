//animation list: flip, slice, box3D, pixel, fade, glide, card

$(document).ready(function () {

    $('#slideWiz').slideWiz({
        auto: true,
        speed: 5000,
        row: 12,
        col: 35,
        animation: [
            'flip',
            'slice',
            'box3D',
           
            'fade',
            'glide',
            'card'
        ],
        file: [
            {
                src: {
                    main: "assets/image/silder/slider-2.jpg",
                    cover: "assets/image/silder/slider-1.jpg"
                },
                title: 'Change Your Look',
                desc: "If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. or randomised words which don't look even slightly believable.",
                descLength: 220,
                button: {
                    text: 'Shop Now',
                    url: '#',
                    class: 'btn btn-medium btn-primary'
                }
            },
            {
                src: {
                    main: "assets/image/silder/slider-6.jpg",
                    cover: "assets/image/silder/slider-2.jpg"
                },
                title: 'Best Brand Shoes',
                desc: "If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. or randomised words which don't look even slightly believable.",
                button: {
                    text: 'Shop Now',
                    url: '#',
                    class: 'btn btn-medium btn-primary'
                }
            },
            {
                src: {
                    main: "assets/image/silder/slider-1.jpg",
                    cover: "assets/image/silder/slider-3.jpg"
                },
                title: 'Change Your Life',
                desc: "If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. or randomised words which don't look even slightly believable.",
                descLength: 190,
                button: {
                    text: 'Shop Now',
                    url: '#',
                    class: 'btn btn-medium btn-primary'
                }
            },
            {
                src: {
                    main: "assets/image/silder/slider-2.jpg",
                    cover: "assets/image/silder/slider-6.jpg"
                },
                title: 'New Style Fashion ',
                desc: "If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. or randomised words which don't look even slightly believable.",
                button: {
                    text: 'Shop Now',
                    url: false,
                    class: 'btn btn-medium btn-primary'
                }
            }
        ]

    });

});
