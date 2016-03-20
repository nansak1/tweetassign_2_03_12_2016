package tweetassign_01

class User {

    transient springSecurityService

    String username
    String password
    boolean enabled = true
    boolean accountLocked = false
    boolean passwordExpired = false

    Set<Role> getAuthorities(){
        UserRole.findAllByUser(this)*.role
    }

    def beforeInsert(){
        encodePassword()
    }

    def beforeUpdate(){
        if (isDirty('password')){
            encodePassword()
        }
    }

    protected void encodePassword(){
        password = springSecurityService?.passwordEncoder ?
                springSecurityService.encodePassword(password):password
    }
    static constraints = {
    }
}
