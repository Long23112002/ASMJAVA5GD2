
const form = document.getElementById('form');
const productSelect = form.querySelector('select[name="searchProduct"]');
const sizeSelect = form.querySelector('select[name="searchSize"]');
const colorSelect = form.querySelector('select[name="searchColor"]');

productSelect.value = sessionStorage.getItem('selectedProduct') || '';
sizeSelect.value = sessionStorage.getItem('selectedSize') || '';
colorSelect.value = sessionStorage.getItem('selectedColor') || '';

form.addEventListener('submit', function (e) {
    e.preventDefault();
    sessionStorage.setItem('selectedProduct', productSelect.value);
    sessionStorage.setItem('selectedSize', sizeSelect.value);
    sessionStorage.setItem('selectedColor', colorSelect.value);
    form.submit();
});