package bitcoin;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlockHashTest {

	@Test
	/*
	 * Checks that root is correctly calculated from previous two children leaflets
	 */
	public void checkMrklRoot() {
		String childA = "0d0eb1b4c4b49fd27d100e9cce555d4110594661b1b8ac05a4b8879c84959bd4";
		String childB = "bfae954bdb9653ceba3721e85a122fba3a585c5762b5ca5abe117b30c36c995e";
		String verifiedRoot = "2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3";

		String calculatedRoot = MerkleTree.getMrklParent(childA, childB);
		assertTrue(verifiedRoot.equals(calculatedRoot));
	}
	
	@Test
	/*
	 * Checks that root is correctly calculated from initial children leaflets
	 */
	public void checkMrklTree() {
		String verifiedRoot = "2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3";
		String[] childrenNodes = {
			    "51d37bdd871c9e1f4d5541be67a6ab625e32028744d7d4609d0c37747b40cd2d",
			    "60c25dda8d41f8d3d7d5c6249e2ea1b05a25bf7ae2ad6d904b512b31f997e1a1",
			    "01f314cdd8566d3e5dbdd97de2d9fbfbfd6873e916a00d48758282cbb81a45b9",
			    "b519286a1040da6ad83c783eb2872659eaf57b1bec088e614776ffe7dc8f6d01"
		};
		assertTrue(verifiedRoot.equals(MerkleTree.getMrklRoot(childrenNodes)));
	}

}
