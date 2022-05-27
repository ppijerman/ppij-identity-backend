package org.ppijerman.ppijidentitybackend.server.service.security.dao.authorization;

public enum Privileges {
    // PERSON
        // LIST
            LIST_PERSON,
        // READ
            READ_PERSON_PUBLIC_DATA,
            READ_PERSON_EMAIL,
            READ_PERSON_UNI_EMAIL,
            READ_PERSON_BIRTH_DATE,
            READ_PERSON_BIRTH_PLACE,
            READ_PERSON_PHONE,
            READ_PERSON_LAST_VERIFIED,
            READ_PERSON_STREET,
            READ_PERSON_STREET_NUMBER,
            READ_PERSON_BRANCH,
            READ_PERSON_ROLES,
            READ_PERSON_EDUCATIONS,
            READ_PERSON_APPLICATIONS,
            READ_PERSON_EXPERIENCES,
            READ_PERSON_SKILLS,
            READ_PERSON_ZIPCODE,
            READ_PERSON_NAME,
        // WRITE
            INPUT_PERSON,
            UPDATE_PERSON_DATA,
            UPDATE_PERSON_PASSWORD,
            UPDATE_PERSON_STATUS,
            UPDATE_PERSON_BRANCH,
            DELETE_PERSON
}
