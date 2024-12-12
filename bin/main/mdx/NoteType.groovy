package mdx

class NoteType {
    String name
    
    static mapping = {
		id generator: "increment"
	}

    static constraints = {
        name nullable: false, blank: false
    }
}