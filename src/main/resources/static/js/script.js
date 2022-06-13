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

    const editShotRecordsBtn = document.getElementById("editShotRecords");

    if (editShotRecordsBtn !== null) {
    editShotRecordsBtn.addEventListener("click", () => {
            let medInfoId = editShotRecordsBtn.value;
            let url = "http://localhost:8080/pet/medInfo/edit/shotRecord?medInfoId=" + medInfoId;
            newPopup(url);

        })
    }

    const editPastSurgeriesBtn = document.getElementById("editPastSurgeries");

    if (editPastSurgeriesBtn !== null) {
    editPastSurgeriesBtn.addEventListener("click", () => {
            let medInfoId = editPastSurgeriesBtn.value;
            let url = "http://localhost:8080/pet/medInfo/edit/pastSurgery?medInfoId=" + medInfoId;
            newPopup(url);
        })
    }


    function newPopup(url) {
    	popupWindow = window.open(
    		url,'popUpWindow','height=400,width=500,left=450,top=300,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes')
    }

    const closeWindowBtn = document.getElementById("btnForClosingAddingShotWindow");

    if (closeWindowBtn !== null) {
        closeWindowBtn.addEventListener("click", () => {
            if (!confirm("Please make sure you click the save button before closing the window, otherwise all unsaved changes will be gone!")) {
            event.preventDefault();} else {
            close();}
        })
    }

    const title = document.getElementById("title");
    const navLinkBtn = document.getElementsByClassName("nav-link");
    const deleteCheckbox = document.getElementsByClassName("deleteCheckbox");

    const checkedOrNot = (collection) => {
        for (let checkbox of collection) {
            if (checkbox.checked) {
            return true;
            }
        }
        return false;
    }


    for (let button of navLinkBtn) {
        button.addEventListener("click", () => {
            if (title.innerHTML.includes("Edit ") || title.innerHTML.includes("Create a Pet Profile") || checkedOrNot(deleteCheckbox))
                if (!confirm("All unsaved changes will be gone. Are you sure you want to continue?")) {
                event.preventDefault();
                }
            }

        )
    }

    const showSlides = () => {
        let i;
        const slides = document.getElementsByClassName("mySlides");

        if (slides !== null) {
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slideIndex++;
            if (slideIndex > slides.length) {slideIndex = 1}
            slides[slideIndex-1].style.display = "block";
            setTimeout(showSlides, 2000);
        }


    }

    let slideIndex = 0;
    showSlides();

//function checkPasswordMatch(fieldConfirmPassword) {
//    if (fieldConfirmPassword.value != $("#password").val()) {
//        fieldConfirmPassword.setCustomValidity("Passwords do not match!");
//    } else {
//        fieldConfirmPassword.setCustomValidity("");
//    }
//}

//function check(input) {
//        if (input.value != document.getElementById('password').value) {
//            input.setCustomValidity('Password Must be Matching.');
//        } else {
//            // input is valid -- reset the error message
//            input.setCustomValidity('');
//        }
}

window.addEventListener("load", init);



