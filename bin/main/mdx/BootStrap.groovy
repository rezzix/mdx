package mdx

class BootStrap {

    def init = { servletContext ->
        if ( !AppUser.findByUsername('test') && System.getProperty("grails.env").equals("development")) {
            def testuser = new AppUser(username: 'test', password: 'test'.encodeAsSHA256(), firstName: 'test', enabled: true).save()
            def testNote1 = new Note(title: 'Test Note', owner: testuser).save()
            def testSubnote1 = new Note(title: 'Test Note', owner: testuser, parent:testNote1).save()
            def testNote2 = new Note(title: 'Test Note 2', owner: testuser).save()
            def testSubnote2 = new Note(title: 'Test Note 2', owner: testuser, parent:testNote2).save()
        }
    }
    def destroy = {
    }
}
