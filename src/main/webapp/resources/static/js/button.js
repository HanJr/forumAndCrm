/**
 *For various buttons include: forum article composing button 
 */

function moveToComposingPage(currentPage, currentBlock){
	location.href = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/forum/composeArticle?" + "currentPage=" + currentPage + "&currentBlock=" + currentBlock;
}

function confirmter(){
	if(confirm("Are you certain you want to delete?")){
		location.href= window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/forum/deleteArticle/" + id;
	}else{
		return false;
	}
}

function editArticle(currentPage, currentBlock){
	location.href=window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/forum/editArticle" + "?id=" + id + "&currentPage=" + currentPage + "&currentBlock=" + currentBlock;;
}

function backToList(currentPage, currentBlock){
	location.href=window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/forum/userForum?currentPage=" + currentPage + "&currentBlock=" + currentBlock;
	
}

var deleteButton = document.getElementById("deleteArticleButton");
var editButton = document.getElementById("editArticleButton");

if(deleteButton != null){
	deleteButton.onclick = confirmter;
}
if(editButton != null){
	editButton.onclick = editArticle;
}

