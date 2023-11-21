package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }


    public Credential getcredentialById(int userId) {
        return credentialMapper.getcredentialById(userId);
    }

    public Credential getCredentialByUsername(String username) {
        return credentialMapper.getCredentialByUsername(username);
    }

    public List <Credential> getAllcredentials(int userId) {
        return credentialMapper.getAllcredentials(userId);
    }

    public void insert(Credential credential) {
        credentialMapper.insert(credential);
    }

    public void update(Credential credential) {
        credentialMapper.update(credential);
    }

    public void delete(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }
}