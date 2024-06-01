document.addEventListener('DOMContentLoaded', function () {
    let cash = document.getElementById('cash');
    let total = document.getElementById('total');
    let refund = document.getElementById('refund');
    let errorCash = document.getElementById('error-cash');
    let pay = document.getElementById('pay');
    let cashValue = parseFloat(cash.value);
    let message = document.getElementById('message');

    if (cash.value === '' || isNaN(cashValue)) {
        pay.disabled = true;
    }
    cash.addEventListener('input', function () {
        let cashValue = parseFloat(cash.value);
        let totalValue = parseFloat(total.value);

        if (cash.value === '' || isNaN(cashValue)) {
            errorCash.textContent = 'Please enter a valid amount';
            pay.disabled = true;
        } else if (cashValue < 0 || cashValue < totalValue) {
            errorCash.textContent = 'Cash must be greater than total money';
            pay.disabled = true;
        } else {
            errorCash.textContent = '';
            pay.disabled = false;
        }

        if (!isNaN(cashValue) && !isNaN(totalValue)) {
            refund.value = (cashValue - totalValue).toFixed(2);
        } else {
            refund.value = '';
        }
    });
});
