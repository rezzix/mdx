<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
    
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${note.title}</h5>
                <p class="card-text">${note.text}</p>
            </div>
            <g:each in="${note.subnotes}" var="subnote">
                <div class="card-body">
                    <h3 class="card-title">${subnote.title}</h3>
                    <p class="card-text">${subnote.text}</p>
                </div>
            </g:each>
            <g:form action="createSubNote">
                <g:hiddenField name="parentid" />
                <g:textField name="title" />
                <g:textArea name="text" />
                <g:submitButton name="save" value="Save" />
            </g:form>
        </div>
    </body>
</html>