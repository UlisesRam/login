//Variables
const cart = document.querySelector('#cart');
const cartContainer = document.querySelector('#cart-list tbody');
const cleanCartBtn = document.querySelector('#clean-cart');
const itemList = document.querySelector('#item-list');
let cartItems = []; //Carrito inicia vacío




loadEventListeners();

function loadEventListeners() {
    //Cuando agregas un item presionando "Agregar al carrito"
    itemList.addEventListener('click', addItem);

    //Elimina items del carrito
    cart.addEventListener("click", deleteItem);

    //Muestra los articulos de LocalStorage
    document.addEventListener('DOMContentLoaded', () => {
        cartItems = JSON.parse(localStorage.getItem('cart')) || [];
        cartHTML();
    })

    //Vaciar el carrito
    cleanCartBtn.addEventListener("click", () => {
        cartItems = [];
        totalAmountAfterDiscount = 0;
        cleanHTML(); //Eliminamos todo el HTML
    });

    //Incrementa la cantidad del articulo del carrito
    cart.addEventListener("click", increaseQuantity);
}


//Funciones
function addItem(e) {
    e.preventDefault();
    if (e.target.classList.contains("add-cart")) {
        const itemSelected = e.target.parentElement.parentElement; //Cada parentElement es salir al padre del elemento seleccionado. Por lo que sale 2 padres a partir de la ubicación del boton
        console.log(itemSelected) //************************ */
        readItemInfo(itemSelected);

    }

}

function increaseQuantity(e) {
    e.preventDefault();
    if (e.target.classList.contains("incQuantity")) { //Si la clase es "incQuantity"
        const itemId = e.target.getAttribute("data-id");
        const shoppingCartTotal = document.querySelector("#total-amount");
        const amountAfterDiscount = document.querySelector("#total-amount-after-discount");

        //Elimina el arreglo de cartItems por el data-id
        for(let i=0; i<cartItems.length; i++){
            if(cartItems[i].id === itemId){
                cartItems[i].quantity = cartItems[i].quantity + 1; //Se incrementa la cantidad
                cartItems[i].amount = cartItems[i].price * cartItems[i].quantity;
            }
        }

        if(cartItems.length === 0){
            shoppingCartTotal.textContent = 0;
            amountAfterDiscount.textContent = 0;
        }

        cartHTML(); //Mandamos a llamar el carrito de nuevo para actualizar el HTML y se muestre el nuevo carrito
        
    }

    if (e.target.classList.contains("decQuantity")) { //Si la clase es "delete-item"
        const itemId = e.target.getAttribute("data-id");
        const shoppingCartTotal = document.querySelector("#total-amount");
        const amountAfterDiscount = document.querySelector("#total-amount-after-discount");

        //Elimina el arreglo de cartItems por el data-id
        for(let i=0; i<cartItems.length; i++){
            if(cartItems[i].id === itemId){
                if(cartItems[i].quantity>1){
                    cartItems[i].quantity = cartItems[i].quantity - 1; //Se incrementa la cantidad
                    cartItems[i].amount = cartItems[i].price * cartItems[i].quantity;
                }
                
                
            }
        }

        if(cartItems.length === 0){
            shoppingCartTotal.textContent = 0;
            amountAfterDiscount.textContent = 0;
        }

        cartHTML(); //Mandamos a llamar el carrito de nuevo para actualizar el HTML y se muestre el nuevo carrito
        
    }
    
}


//Elimina un curso del carrito
function deleteItem(e) {
    e.preventDefault();
    if (e.target.classList.contains("delete-item")) { //Si la clase es "delete-item"
        const itemId = e.target.getAttribute("data-id");
        const shoppingCartTotal = document.querySelector("#total-amount");
        const amountAfterDiscount = document.querySelector("#total-amount-after-discount");

        //Elimina el arreglo de cartItems por el data-id
        cartItems = cartItems.filter(item => item.id !== itemId);

        if(cartItems.length === 0){
            shoppingCartTotal.textContent = 0;
            amountAfterDiscount.textContent = 0;
        }

        cartHTML(); //Mandamos a llamar el carrito de nuevo para actualizar el HTML y se muestre el nuevo carrito
        
    }
    
}

//Lee el contenido del HTML al que le dimos click y extrae la información del item 
function readItemInfo(item) {
    //console.log(item);

    //Crear un objeto con el contenido del item actual
    const itemInfo = {
        image: item.querySelector("img").src,
        title: item.querySelector("h4").textContent,
        price: parseFloat(item.querySelector("p").textContent),
        priceAfterDiscount: parseFloat(item.querySelector("p").textContent),
        id: item.querySelector("a").getAttribute("data-id"),
        category: item.querySelector("span").textContent,
        amount: parseFloat(item.querySelector("p").textContent),
        quantity: 1

    }

    //Revisa si un elemento ya existe en el carrito
    const exists = cartItems.some(item => item.id === itemInfo.id) //Si tenemos dos elementos en el carrito iguales...
    if (exists) {
        //Actualizamos la cantidad
        const items = cartItems.map(item => { //.map itera sobre los elementos del carrito
            if (item.id === itemInfo.id) { //Si el curso que queremos agregar tiene el mismo id que un item que ya está en el carrito, ...
                item.quantity++;
                item.amount += item.price;
                return item; //Retorna el objeto actualizado
            } else {
                return item; //Retorna los objetos que no son los duplicado
            }
        });
        cartItems = [...items];
    } else {
        //Agregamos el item en el carrito
        cartItems = [...cartItems, itemInfo]; //Tomamos una copia del carrito de compras y le iremos agregando el objeto de itemInfo
    }


    //Agrega elementos al arreglo del carrito
    console.log(cartItems)
    cartHTML();


    var itemquantity = document.querySelectorAll("amount");
    console.log(itemquantity)
}

//Muestra el carrito de compras en el HTML
function cartHTML() {
    //Limpiar el HTML
    cleanHTML();
    //Recorre el carrito y genera el HTML
    cartItems.forEach(item => {
        const { image, title, price, priceAfterDiscount, quantity, id, category, amount } = item;
        const row = document.createElement("tr");
        row.innerHTML = `
        <td>
            <img src="${image}" width="100">
        </td>
        <td>${title}</td>
        <td class="priceStyle price">${price}</td>
        <td class="priceADStyle price">${priceAfterDiscount}</td>
        <td><input type="text" value="${quantity}"></input></td>
        <td>
            <a class="btn incQuantity" onclick="increaseQuantity()" data-id="${id}">+</a>
            <a class="btn decQuantity" onclick="decreaseQuantity()" data-id="${id}">-</a>

        </td>
        <td>${category}</td>
        <td class="price amount">${amount}</td>
        <td>
            <a href="#" class="delete-item" data-id="${id}"> X </a>
        </td>
        `;

        //Agrega HTML del carrito en el Tbody
        cartContainer.appendChild(row);

        updateShoppingCartTotal();
    })

    //Agregar el carrito de compras al Storage
    syncStorage();
}

function syncStorage(){
    localStorage.setItem('cart', JSON.stringify(cartItems))
}

//Elimina los items del tbody
function cleanHTML() {
    //Forma lenta
    //contenedorCarrito.innerHTML = '';
    const shoppingCartTotal = document.querySelector("#total-amount");

    while (cartContainer.firstChild) {
        cartContainer.removeChild(cartContainer.firstChild);
    }

    if(cartItems.length === 0){
        shoppingCartTotal.textContent = 0;
    }
}

function updateShoppingCartTotal() {
    let total = 0;
    const shoppingCartTotal = document.querySelector("#total-amount");
    //console.log("shoppingCartTotal "+shoppingCartTotal)

    cartItems.forEach(function (item) {
        total += item.amount;
        shoppingCartTotal.textContent = total;
        console.log("Total: " + total)

        if(cartItems.length === 0){
            shoppingCartTotal.textContent = 0;
        }
    });


}

getPrice = function() {
    var hideField = document.querySelector(".coupon-apply");
    var coupon = document.getElementById("coupon").value;
    var discount = 0.15;
    var i;

    let totalAmountAfterDiscount = 0;
    let amountAfterDiscount = document.querySelector("#total-amount-after-discount");

    if(coupon==="discount15"){ //Si se aplica el cupon existente
        for(i=0; i<cartItems.length; i++){ //Recorremos el arreglo del carrito
            if(cartItems[i].category === "soccer"){ //Si la categoria del item es "soccer"
                cartItems[i].priceAfterDiscount = cartItems[i].price - (cartItems[i].price * discount); //El precio del item actual es igual al precio actual menos el descuento
                cartItems[i].amount = cartItems[i].price * cartItems[i].quantity; //El amount del item es igual al precio actual por la cantidad actual
                //cartItems[i].price.classList.add
                cartHTML();
                updateShoppingCartTotal();
            }
        }

        alert("Coupon applied! Discount: "+(discount*100)+"%")

        hideField.setAttribute("hidden", true); //Oculta el campo del cupon una vez se aplique alguno 
        //hideField.remove();

        

        //Total after discount
    for(var j=0; j<cartItems.length; j++){
        totalAmountAfterDiscount = totalAmountAfterDiscount + ((cartItems[j].priceAfterDiscount)*(cartItems[j].quantity));
        amountAfterDiscount.textContent = totalAmountAfterDiscount;
    }

    //CSS to Total 
    const totalAfterDiscountStyle = document.getElementById("total-amount-after-discount");
    totalAfterDiscountStyle.classList.add("priceAfterDiscount-style");

    const totalStyle = document.getElementById("total-amount");
    totalStyle.classList.add("price-style");

    //CSS to Price and Price After Discount
    const priceAfterDiscountStyle = document.querySelectorAll(".priceADStyle");
    for(let k=0; k<priceAfterDiscount.length; k++){
        priceAfterDiscountStyle[i].style.color = "green";
    }

    }else{
        alert("Coupon not valid.")
    }
}