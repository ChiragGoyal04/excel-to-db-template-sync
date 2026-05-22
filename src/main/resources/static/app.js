document.getElementById("uploadForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        const fileInput = document.getElementById("excelFile");

        if (fileInput.files.length === 0) {
            alert("Please select an Excel file.");
            return;
        }
        const formData = new FormData();

        formData.append("file", fileInput.files[0]);
        try {
            const response = await fetch("/api/upload", {

                method: "POST",
                body: formData

            });

            const result = await response.json();

            if (response.ok) {

                displayTable(result);

            } else {

               alert("Upload failed. Please try again later!");
            }

        } catch (error) {

            console.log(error);
            alert("Server Error!");
        }

    }
    );


function displayTable(data){

    const resultDiv =
        document.getElementById("result");

    resultDiv.innerHTML = "";

    let table = "<table>";

    data.forEach((row, rowIndex) => {

        table += "<tr>";

        row.forEach(cell => {

            if(rowIndex === 0){

                table += `<th>${cell}</th>`;

            }else{

                table += `<td>${cell}</td>`;
            }

        });

        table += "</tr>";

    });

    table += "</table>";

    resultDiv.innerHTML = table;
}