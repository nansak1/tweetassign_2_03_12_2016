package tweetassign_01

import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


class Account {

    transient springSecurityService

    String accountHandle
    String fullName
    String emailAddress
    String accountPassword
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    Set<Role> getAuthorities(){
        UserRole.findAllByUser(this)*.role
    }

    def beforeInsert(){

        encodePassword()

    }

    def beforeUpdate(){
        if (isDirty('accountPassword')){

            encodePassword()
    }
    }

    protected void encodePassword(){

        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String hashedPassword = PasswordEncoder.encode(accountPassword);

       //accountPassword = springSecurityService.passwordEncoder ? springSecurityService.encodePassword(accountPassword):accountPassword
        accountPassword = springSecurityService?.passwordEncoder ?
                springSecurityService.encodePassword(accountPassword): accountPassword
    }


    static hasMany=[msg:Message,followers:Account,following:Account]
    static constraints = {
        accountHandle nullable:false, blank:false, unique:true
        fullName nullable:false, blank:false
        emailAddress nullable:false, blank:false, unique:true
        accountPassword nullable:false, blank:false/*, size:8..16,matches:"^.(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*\$"*/

    }



}
