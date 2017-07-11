package com.github.cangoksel.rest;

import com.github.cangoksel.belge.Belge;
import com.github.cangoksel.belge.BelgeService;
import com.github.cangoksel.belge.BelgeTipi;
import com.github.cangoksel.common.exceptions.DomainException;
import com.github.cangoksel.common.rest.SimpleRestResponse;
import com.github.cangoksel.model.ProfilMobileModel;
import com.github.cangoksel.user.Kullanici;
import com.github.cangoksel.user.UserService;
import com.github.cangoksel.user.model.BelgeMobileModel;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.cangoksel.user.CurrentUserContext;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by tozyurek on 18.07.2016.
 */
@RestController
@RequestMapping("/mobile")
public class MobileRestService {

    @Autowired
    private CurrentUserContext userContext;

    @Autowired
    private UserService userService;

    @Autowired
    private BelgeService belgeService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ProfilMobileModel login() {
        return new ProfilMobileModel(new Kullanici(userContext.getKullanici().getEposta()));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/profilContent", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ProfilMobileModel getProfilContent() {
        return new ProfilMobileModel((Kullanici) userContext.getKullanici());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void changePassword(@RequestBody ProfilMobileModel profilMobileModel) {
        userService.changePassword(profilMobileModel.getEskiSifre(), profilMobileModel.getYeniSifre());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/gecmis/dava/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Kullanici> getDavaHareketleri(@PathVariable("id") UUID id) {
        return userService.findKullanicilarByMusteriId(id).stream().collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/kisi/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Kullanici> getKisiList(@PathVariable("id") UUID id) {
        return userService.findKullanicilarByMusteriId(id).stream().collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/notEkle/{dosyaId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void notEkle(@PathVariable("dosyaId") UUID dosyaId, @RequestBody BelgeMobileModel model) {
        belgeService.belgeById(dosyaId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/masrafKaydet/{dosyaId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public SimpleRestResponse yeniMasrafKaydet(@PathVariable("dosyaId") UUID dosyaId,
        @RequestBody BelgeMobileModel model) {
        Belge masrafTipi = belgeService.belgeById(UUID.fromString(model.getBelgeTipi().getLabel()));
        return new SimpleRestResponse("OK");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<BelgeMobileModel> query(@RequestBody String text) {
        return (List<BelgeMobileModel>) belgeService.belgeEkle(Collections.EMPTY_LIST);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/query/sistemNo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BelgeMobileModel queryBySistemNo(@RequestBody String text) {
        long id;
        try {
            id = Long.parseLong(text);
        } catch (NumberFormatException e) {
            return null;
        }
        try {
            return new BelgeMobileModel("", "", new byte[]{}, BelgeTipi.BELGE);
        } catch (DomainException e) {
        }
        return null;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/sifremiUnuttum/bilgi", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ProfilMobileModel profilBilgileriGetir(@RequestBody String eposta) {
        return new ProfilMobileModel(userService.fetchKullaniciByEposta(eposta));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/sifremiUnuttum/onay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void sifremiUnuttum(@RequestBody String eposta) {
        Object o = String.valueOf("asa");
        HttpServletRequest request = (HttpServletRequest) o; //(HttpServletRequest)context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        url = url.substring(0, url.lastIndexOf("/"));
        userService.resetPassword(eposta, url);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/updalBelge/{dosyaId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void belgeUpload(@PathVariable("dosyaId") UUID dosyaId, @RequestBody BelgeMobileModel belgeModel)
        throws IOException {
        final InputStream inputStream = new ByteArrayInputStream(belgeModel.getContent());
        final int size = inputStream.available();
        final BelgeTipi belgeTipi = belgeModel.getBelgeTipi();
        final Belge belge = new Belge(belgeTipi, belgeModel.getName(), size, true);

        belgeService.belgeEkle(belge, inputStream);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/durusmaEkleme", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public SimpleRestResponse durusmaEkle(@RequestBody BelgeMobileModel model) {
        try {
            userService.setLoginedKullanici(model.getName().toString());
        } catch (DomainException e) {
            return new SimpleRestResponse(e.getMessage());
        }
        return new SimpleRestResponse("OK");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/gosterilecekBilgiNotlari/{dosyaId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<Kullanici> gosterilecekBilgiNotlariGetir(@PathVariable("dosyaId") UUID dosyaId) {

        return Lists.newArrayList();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/bilgiNotuOkunduYap/{bilgiNotuId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public void bilgiNotuOkunduYap(@PathVariable("bilgiNotuId") UUID bilgiNotuId) {
        userService.setLoginedKullanici(bilgiNotuId.toString());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/uyapXml/{id}", method = RequestMethod.GET, produces = "application/xml;charset=UTF-8")
    public ResponseEntity fetchUyapXml(@PathVariable("id") UUID dosyaId) {
        String exchangeData = "asa";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(String.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(
                "com.sun.xml.bind.xmlHeaders",
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            );
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            File file = File.createTempFile("uyap", "xml");
            jaxbMarshaller.marshal(exchangeData, file);

            final byte[] body = Files.toByteArray(file);

            return ResponseEntity.status(HttpStatus.OK)
                                 .contentType(MediaType.APPLICATION_XML)
                                 .header("content-disposition", "attachment; filename=uyap.xml")
                                 .body(body);
        } catch (IOException | JAXBException ex) {
            throw new DomainException(ex);
        }
    }
}
