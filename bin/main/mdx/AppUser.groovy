package mdx

class AppUser {
    String username
    String password
    String firstName
    String lastName
    String title
    String email
    String phone
    boolean enabled
    Date dateOfBirth

    static constraints = {
        username blank: false, unique: true
        password blank: false
        firstName nullable: true, blank: false, size: 1..100
        lastName nullable: true, blank: false, size: 1..100
        title nullable: true
        email email: true, nullable: true
        phone nullable: true, maxSize: 200, matches: /[\+]{0,1}[0-9\s]{3,15}/
        dateOfBirth nullable: true
        enabled nullable: false, defaultValue: false, type: 'org.hibernate.type.NumericBooleanType'
    }

    def normalize() {
        this.firstName = this.firstName ? this.firstName.trim().toLowerCase() : this.firstName
        this.lastName = this.lastName ? this.lastName.trim().toLowerCase() : this.lastName
        this.email = this.email ? this.email.trim().toLowerCase() : this.email
    }

    static mapping = {
        sort lastName: "asc"
    }

    Map getInfoMap() {
        return [
                id          : id,
                username    : username,
                firstName   : firstName,
                lastName    : lastName,
                title       : title,
                email       : email,
                phone       : phone,
                dateOfBirth : dateOfBirth,
                enabled     : enabled
        ]
    }

    String toString() {
        getCompleteName()
    }

    String getCompleteName() {
        return (title ? title + ' ' : '') + Utils.capitalize((firstName ? firstName : '') + ' ' + lastName)
    }

    String getCompleteTitleName() {
        return Utils.capitalize((firstName ? firstName : '') + ' ' + lastName) + ' (' + (title ?: '') + ')'
    }

    String shortName() {
        return (title ? title + ' ' : '') + Utils.capitalize(lastName)
    }

}
