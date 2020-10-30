function prev(currentPage, currentBlock, displayingPagePerBlock){
	var currentPage = ((currentBlock - 2) * displayingPagePerBlock) + 1;
	var currentBlock = currentBlock - 1;
	
	var url = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/forum/userForum?";
	url = url + "currentPage=" + currentPage + "&currentBlock=" + currentBlock;
	
	location.href= url;
} 


function fn_pagination(currentPage, currentBlock){
	
	var url = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/forum/userForum?";
	url = url + "currentPage=" + currentPage + "&currentBlock=" + currentBlock;
	
	location.href = url;
}

function next(currentPage, currentBlock, displayingPagePerBlock){
	
	var currentPage = ((currentBlock * displayingPagePerBlock)) + 1;
	var currentBlock = parseInt(currentBlock) + 1;
	
	var url = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/forum/userForum?";
	url = url + "currentPage=" + currentPage + "&currentBlock=" + currentBlock;
	
	location.href = url;	
}