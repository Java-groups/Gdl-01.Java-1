'use strict';

const openClosed = document.querySelector('#openClosedContainer');

loadEventListeners();
function loadEventListeners() {
    openClosed.addEventListener('click', switchSurveys);
}

function switchSurveys(e){
    if (e.target.classList.contains('#openedSurveyBtn')){
        prompt("clicked Open button");
        showOpenedSurveys();
    } else if (e.target.classList.contains('#closedSurveyBtn')){
        prompt("clicked closed button");
        showClosedSurveys();
    }
}

function showOpenedSurveys() {
    cleanHTML();

    let tableHeader = document.querySelector('.card-body table thead');
    let trow = document.createElement('tr');

    trow.innerHTML = `
        <th class="font-weight-bold text-gray-800">Question</th>
        <th class="font-weight-bold text-gray-800">Status</th>
        <th class="font-weight-bold text-gray-800">Responses</th>
    `;

    tableHeader.appendChild(trow);
}

function showClosedSurveys() {
    cleanHTML();

    let tableHeader = document.querySelector('.card-body table thead');
    let trow = document.createElement('tr');

    trow.innerHTML = `
        <th class="font-weight-bold text-gray-800">Question</th>
        <th class="font-weight-bold text-gray-800">Closed Date</th>
        <th class="font-weight-bold text-gray-800">Responses</th>
    `;

    tableHeader.appendChild(trow);
}

function cleanHTML(){
    let theader = document.querySelector('.card-body table thead');
    while (theader.firstChild){
        theader.removeChild(theader.firstChild);
    }
}
