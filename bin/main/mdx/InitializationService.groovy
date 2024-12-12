package mdx

class InitializationService {
        
    def checkMinimalTypes() {
        if (NoteType.count() == 0) {
            new NoteType(name: 'pro').save()
            new NoteType(name: 'perso').save()
            println "NoteTypes created"
        }
    }

}