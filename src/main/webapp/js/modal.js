document.addEventListener('DOMContentLoaded', function () {
    var modalProduct = document.getElementById('modalProduct');
    modalProduct.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let submitCart = modalProduct.querySelector('#submitCart');
        let errorQuantityCash = modalProduct.querySelector('#error-quantity-cash');
        let id = button.getAttribute('data-id');
        let code = button.getAttribute('data-code');
        let product = button.getAttribute('data-product');
        let color = button.getAttribute('data-color');
        let size = button.getAttribute('data-size');
        let price = button.getAttribute('data-price');
        let quantity = button.getAttribute('data-quantity');
        let quantityInput = modalProduct.querySelector('input[name="quantity"]');
        let priceModal = modalProduct.querySelector('input[name="price"][disabled]');
        let totalMoneyInput = modalProduct.querySelector('input[name="totalMoney"][readonly]');

        modalProduct.querySelector('input[name="code"]').value = code;
        modalProduct.querySelector('input[name="product"][disabled]').value = product;
        modalProduct.querySelector('input[name="color"][disabled]').value = color;
        modalProduct.querySelector('input[name="size"][disabled]').value = size;
        quantityInput.value = 1;
        priceModal.value = price;
        totalMoneyInput.value = (quantityInput.value * priceModal.value).toFixed(1)
        quantityInput.addEventListener('input', function () {
            totalMoneyInput.value = (quantityInput.value * priceModal.value).toFixed(1);
        });

        quantityInput.addEventListener('input', function () {
            let inputQuantity = parseInt(quantityInput.value, 10);
            let availableQuantity = parseInt(quantity, 10);

            totalMoneyInput.value = (inputQuantity * priceModal.value).toFixed(1);

            if (inputQuantity > availableQuantity || inputQuantity < 1) {
                errorQuantityCash.textContent = 'Quantity exceeds available stock';
                submitCart.disabled = true;
            } else if (inputQuantity < 1) {
                errorQuantityCash.textContent = 'Quantity must be greater than 0';
                submitCart.disabled = true;
            } else {
                errorQuantityCash.textContent = '';
                submitCart.disabled = false;
            }
        });

        let modalTitle = modalProduct.querySelector('.modal-title');
        modalTitle.textContent = `Product ${product}`;
    });
});


window.addEventListener('load', function () {
    let pay = document.getElementById('pay');
    let messageElement = document.getElementById('message');

    pay.addEventListener('click', function () {
        let sessionMessage = "Payment successful";

        if (sessionMessage) {
            messageElement.textContent = sessionMessage;

            setTimeout(function () {
                messageElement.textContent = '';
            }, 3000);
        }
    });
});
