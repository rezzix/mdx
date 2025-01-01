package mdx

import grails.gorm.transactions.Transactional
import grails.converters.JSON

@Transactional
class NoteApiController {
    
    def list() {
        //def notes = Note.findAllByParentIsNull()
        def notes = Note.findAll()
        println "Notes: $notes"
        render notes as JSON
    }

    def show() {
        def note = Note.get(params.id)
        if (note) {
            render note as JSON
        } else {
            render status: 404 // Returns a 404 Not Found status
        }
        
    }

    def createTopNote() {
        def note = new Note(title: params.title, owner: AppUser.get(request.user.id))
        println request.user.id
        println request.user.id.class
        println AppUser.findAll()
        println AppUser.get(request.user.id)
        note.save(flush: true, failOnError: true)
        render note as JSON
    }

    def createSubNote() {
        def parent = Note.get(params.pareniId)
        if (!parent) {
            redirect(action: "list")
            return
        }
        def note = new Note(title: params.title, parent: parent, owner: AppUser.get(request.user.id))
        note.save(flush: true)
        render note as JSON
    }
}
