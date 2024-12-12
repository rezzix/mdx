package mdx

import grails.gorm.transactions.Transactional

@Transactional
class NoteController {
    
    def index() {
        render "Welcome to miami"
    }
    def list() {
        [notes: Note.findAllByParentIsNull()]
    }

    def show() {
        def note = Note.get(params.id)
        if (!note) {
            redirect(action: "list")
            return
        }
        [note: note]
    }

    def createTopNote() {
        def note = new Note(title: params.title)
        note.save(flush: true, failonError: true)
        println "Note id: ${note.id}"
        redirect(action: "show", id: note.id)
    }

    def createSubNote() {
        def parent = Note.get(params.pareniId)
        if (!parent) {
            redirect(action: "list")
            return
        }
        def note = new Note(title: params.title, parent: parent)
        note.save(flush: true)
        redirect(action: "show", id: parent.id)
    }
}
