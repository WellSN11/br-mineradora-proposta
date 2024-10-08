package org.br.mineradora.controller;

import io.quarkus.security.Authenticated;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.service.ProposalService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/proposal")
@Authenticated
public class ProposalController {

    private final Logger LOG = LoggerFactory.getLogger(ProposalController.class);

    @Inject
    JsonWebToken jsonWebToken;
    @Inject
    ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "manager"})
    public ProposalDetailsDTO getFullProposal(@PathParam("id") long id){
        return proposalService.findFullProposal(id);
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO){

        LOG.info("--- Recebendo uma proposta de compra ---");

        try{
            proposalService.createNewProposal(proposalDetailsDTO);

            return Response.ok().build();

        }catch (Exception e){

            return Response.serverError().build();

        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("manager")
    public Response deleteProposal(@PathParam("id") long id){

        try{
            proposalService.removeProposal(id);

            return Response.ok().build();
        }catch(Exception e){

            return  Response.serverError().build();
        }

    }


}
