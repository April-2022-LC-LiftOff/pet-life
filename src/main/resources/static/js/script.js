init = () => {

    const switchToAgeBtn = document.getElementById("switchToAgeBtn");
    const switchToBirthdayBtn = document.getElementById("switchToBirthdayBtn");
    const ageField = document.getElementById("ageField");
    const date = new Date().toISOString().slice(0,10);


    if (switchToAgeBtn !== null && switchToBirthdayBtn !== null) {
            console.log(date);

             switchToBirthdayBtn.addEventListener("click", () => {
                    switchToBirthdayBtn.style.display = "none";
                    switchToAgeBtn.style.display = "block";
                    let html = '<label>Birthday' +
                                    `<input type="date" class="form-control" id="bDate" name="bDate" max="${date}" required>` +
                                    '</label>';

                        ageField.innerHTML = html;
                    })


        switchToAgeBtn.addEventListener("click", () => {
        switchToBirthdayBtn.style.display = "block";
        switchToAgeBtn.style.display = "none";
            let html = '<label>Age' +
                            '<input id="ageYear" name="ageYear" type="number" max="150" min="0" class="form-control" style="width:100px;" placeholder="Year">' +
                            '<input id="ageMonth" name="ageMonth" type="number" max="11" min="1" class="form-control" style="width:100px;" placeholder="Month">' +
                            '</label>';
                   ageField.innerHTML = html;
               })
    }





    const showSlides = () => {
        let i;
        let slides = document.getElementsByClassName("mySlides");
        let dots = document.getElementsByClassName("dot");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {slideIndex = 1}
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex-1].style.display = "block";
        dots[slideIndex-1].className += " active";
        setTimeout(showSlides, 2000); // Change image every 2 seconds


    }

    let slideIndex = 0;
    showSlides();

}

window.addEventListener("load", init);



