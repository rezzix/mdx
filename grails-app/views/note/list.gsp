<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
        <g:each var="note" in="${notes}">
            <div class="card">
                <div class="card-body">
                    <g:link action="show" id="${note.id}"><h5 class="card-title">${note.title}</h5></g:link>
                    <p class="card-text">${note.text}</p>
                </div>
            </div>
        </g:each>

            
        <g:form action="createTopNote">
            <g:textField name="title"  />
            <g:textArea name="text"  />
            <g:submitButton name="save" value="Save" />
        </g:form>
    </body>
</html>