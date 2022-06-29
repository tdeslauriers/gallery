package world.deslauriers.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import world.deslauriers.domain.Album;

@MicronautTest
public class AlbumDaoTest {
	
	@Inject
	private final AlbumRepository albumDao;

	public AlbumDaoTest(AlbumRepository albumDao) {
		this.albumDao = albumDao;
	}

	@Test
	void testAlbumDaoCrud() {
		
		var judo = new Album("Judo");
		
		judo = albumDao.save(judo);
		assertNotNull(judo.id());
		assertEquals("Judo", judo.album());
		
		var find = albumDao.findById(judo.id()).get();
		assertNotNull(find.id());
		assertEquals("Judo", find.album());

		var update = new Album(find.id(), "Soccer");

		find = albumDao.update(update);
		assertEquals("Soccer", find.album());

		var albums = albumDao.findAll();
		assertTrue(albums.iterator().hasNext());
		albums.forEach(album -> System.out.println(album.toString()));
		
		albumDao.deleteById(find.id());
		var deleted = albumDao.findById(find.id());
		assertTrue(deleted.isEmpty());

		// test data, includes xrefs
		var xref = albumDao.findByAlbum("2019").get();
		assertTrue(xref.albumImages().iterator().hasNext());
	}
}
