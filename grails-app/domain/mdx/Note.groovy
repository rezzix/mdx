package mdx

class Note {
    String title
    String text
    Note parent
    NoteType type

    static hasMany = [subnotes: Note] 

    static mapping = {
		id generator: "increment"
	}

    static constraints = {
        title nullable: false, blank: false
        text nullable: true
        parent nullable: true
        type nullable: true
    }
}
