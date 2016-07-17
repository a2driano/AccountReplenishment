$(document).ready(function () {
    onStart();
});
var id;
var email;
var balanceOld;
var onStart = function () {
    /**open modal window*/
    $('a#go').click(function (event) {
        $('#alert_message').text('');
        $('#sum').val('');
        id = $(this).attr('index');
        balanceOld = $(this).closest('td').next('td').attr('index');
        email = $(this).text();
        event.preventDefault();
        $('#overlay').fadeIn(200,
            function () {
                $('#name_user').text('Пользователь: ' + email);
                $('#modal_form')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 100);
            });
    });
    /**close modal window*/
    $('#modal_close, #overlay, #modal_button_close').click(function () {
        $('#modal_form')
            .animate({opacity: 0, top: '15%'}, 200,
            function () {
                $(this).css('display', 'none');
                $('#overlay').fadeOut(400);
            }
        );
    });
    /**fill money to balance of user to press enter*/
    $('#sum').keypress(function (e) {
        if (e.keyCode == 13)$('#modal_button_click').click(fillMoney());
    })
    /**fill money to balance of user to click the button*/
    $('#modal_button_click').on('click', function () {
        fillMoney()
    });
    /**fill money function*/
    function fillMoney() {
        var balance = $('#sum').val();
        if (!$.isNumeric(balance)) {
            $('#alert_message').html('');
            $('#alert_message').append('Неверный формат суммы');
        } else {
            $('#alert_message').text('');
            var data = {
                date: new Date(),
                sum: balance,
                user: {
                    idUser: id,
                    balance: balance
                }
            };
            console.log(data);
            $.ajax({
                url: $hostRoot + 'add',
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (status) {
                    if (status.status == 'SUCCESS') {
                        $('#modal_form').hide(200);
                        $('#overlay').fadeOut(400);
                        $('a#go').filter('[index="' + id + '"]').closest('td').next('td')
                            .text(number_format((parseFloat(balance) + parseFloat(balanceOld)), 2, ',', ' ') + ' $');
                        $('a#go').filter('[index="' + id + '"]').closest('td').next('td')
                            .attr('index', Number(balance) + Number(balanceOld));
                    } else {
                        console.log(data);
                    }
                },
                error: function (error) {
                    console.log('ERROR:' + error);
                    alert('Платеж не прошел! Повторите через 5 минут.');
                }
            })
        }
    };

    /**function convert balace*/
    function number_format(number, decimals, dec_point, thousands_sep) {
        // *     example 2: number_format(1234.56, 2, ',', ' ');
        // *     returns 2: '1 234,56'
        var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
            toFixedFix = function (n, prec) {
                // Fix for IE parseFloat(0.55).toFixed(0) = 0;
                var k = Math.pow(10, prec);
                return Math.round(n * k) / k;
            },
            s = (prec ? toFixedFix(n, prec) : Math.round(n)).toString().split('.');
        if (s[0].length > 3) {
            s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
        }
        if ((s[1] || '').length < prec) {
            s[1] = s[1] || '';
            s[1] += new Array(prec - s[1].length + 1).join('0');
        }
        return s.join(dec);
    }
};



