var button = document.querySelector('button');
var form = document.querySelector('#blablabla');
var popup = document.querySelector('.popup');

button.addEventListener('click', () => {
form.classList.add('open');
popup.classList.add('popup_open');
});
