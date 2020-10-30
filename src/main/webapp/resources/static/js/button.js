/**
 *For various buttons include: forum article composing button 
 */

function moveToComposingPage(){
	location.href = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/forum/composeArticle";
}

function confirmter(){
	if(confirm("Are you certain you want to delete?")){
		location.href= window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/forum/deleteArticle/" + id;
	}else{
		return false;
	}
}

function editArticle(){
	location.href=window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/forum/editArticle" + "?id=" + id;
}

function backToList(){
	location.href=window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/forum/userForum";
}

var composeButton = document.getElementById("composeArticle");
var deleteButton = document.getElementById("deleteArticleButton");
var editButton = document.getElementById("editArticleButton");
var backToListButton = document.getElementById("backToListButton");

if(composeButton != null){
	composeButton.onclick = moveToComposingPage;
}
if(deleteButton != null){
	deleteButton.onclick = confirmter;
}
if(editButton != null){
	editButton.onclick = editArticle;
}
if(backToListButton != null){
	backToListButton.onclick = backToList;
}
