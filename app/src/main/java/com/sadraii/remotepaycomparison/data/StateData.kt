package com.sadraii.remotepaycomparison.data

enum class StateTaxMethod {
    NONE,
    FLAT,
    PROGRESSIVE
}

data class State(
    val name: String,
    val taxMethod: StateTaxMethod,
    val taxBracketsSingle: List<TaxBracket>? = null,
    val taxBracketsMarried: List<TaxBracket>? = null
)

data class TaxBracket(val from: Int, val to: Int, val rate: Double)

object StateData {
    val states = listOf<State>(
        State(
            "Alabama",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 500, 0.02),
                TaxBracket(501, 3_000, 0.04),
                TaxBracket(3_001, -1, 0.05)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 1_000, 0.02),
                TaxBracket(1_001, 6_000, 0.04),
                TaxBracket(6_001, -1, 0.05)
            )
        ),
        State(
            "Alaska",
            StateTaxMethod.NONE
        ),
        State(
            "Arizona",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 27_272, 0.0259),
                TaxBracket(27_273, 54_544, 0.0334),
                TaxBracket(54_545, 163_632, 0.0417),
                TaxBracket(163_633, 250_000, 0.045),
                TaxBracket(250_001, -1, 0.08)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 54_544, 0.0259),
                TaxBracket(54_545, 109_088, 0.0334),
                TaxBracket(109_089, 327_263, 0.0417),
                TaxBracket(327_264, 500_000, 0.045),
                TaxBracket(500_001, -1, 0.08)
            )
        ),
        State(
            "Arkansas",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 4_000, 0.02),
                TaxBracket(4_001, 8_000, 0.04),
                TaxBracket(8_001, -1, 0.059)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 4_000, 0.02),
                TaxBracket(4_001, 8_000, 0.04),
                TaxBracket(8_001, -1, 0.059)
            )
        ),
        State(
            "California",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 8_932, 0.01),
                TaxBracket(8_933, 21_175, 0.02),
                TaxBracket(21_176, 33_421, 0.04),
                TaxBracket(33_422, 46_394, 0.06),
                TaxBracket(46_395, 58_634, 0.08),
                TaxBracket(58_635, 299_508, 0.093),
                TaxBracket(299_509, 359_407, 0.103),
                TaxBracket(359_408, 599_012, 0.113),
                TaxBracket(599_013, 1_000_000, 0.123),
                TaxBracket(1_000_001, -1, 0.133)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 17_864, 0.01),
                TaxBracket(17_865, 42_350, 0.02),
                TaxBracket(42_351, 66_842, 0.04),
                TaxBracket(66_843, 92_788, 0.06),
                TaxBracket(92_789, 117_268, 0.08),
                TaxBracket(117_269, 599_016, 0.093),
                TaxBracket(599_017, 718_814, 0.103),
                TaxBracket(718_815, 1_000_000, 0.113),
                TaxBracket(1_000_001, 1_198_024, 0.123),
                TaxBracket(1_198_025, -1, 0.133)
            )
        ),
        State(
            "Colorado",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0455)
            )
        ),
        State(
            "Connecticut",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 10_000, 0.03),
                TaxBracket(10_001, 50_000, 0.05),
                TaxBracket(50_001, 100_000, 0.055),
                TaxBracket(100_001, 200_000, 0.06),
                TaxBracket(200_001, 250_000, 0.065),
                TaxBracket(250_001, 500_000, 0.069),
                TaxBracket(500_001, -1, 0.0699)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 20_000, 0.03),
                TaxBracket(20_001, 100_000, 0.05),
                TaxBracket(100_001, 200_000, 0.055),
                TaxBracket(200_001, 400_000, 0.06),
                TaxBracket(400_001, 500_000, 0.065),
                TaxBracket(500_001, 1_000_000, 0.069),
                TaxBracket(1_000_001, -1, 0.0699)
            )
        ),
        State(
            "Delaware",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 2_000, 0.0),
                TaxBracket(2_001, 5_000, 0.022),
                TaxBracket(5_001, 10_000, 0.039),
                TaxBracket(10_001, 20_000, 0.048),
                TaxBracket(20_001, 25_000, 0.052),
                TaxBracket(25_001, 60_000, 0.0555),
                TaxBracket(60_001, -1, 0.066)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 2_000, 0.0),
                TaxBracket(2_001, 5_000, 0.022),
                TaxBracket(5_001, 10_000, 0.039),
                TaxBracket(10_001, 20_000, 0.048),
                TaxBracket(20_001, 25_000, 0.052),
                TaxBracket(25_001, 60_000, 0.0555),
                TaxBracket(60_001, -1, 0.066)
            )
        ),
        State(
            "District of Columbia",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 10_000, 0.04),
                TaxBracket(10_001, 40_000, 0.06),
                TaxBracket(40_001, 60_000, 0.065),
                TaxBracket(60_001, 350_000, 0.085),
                TaxBracket(350_001, 1_000_000, 0.0875),
                TaxBracket(1_000_001, -1, 0.0895)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 10_000, 0.04),
                TaxBracket(10_001, 40_000, 0.06),
                TaxBracket(40_001, 60_000, 0.065),
                TaxBracket(60_001, 350_000, 0.085),
                TaxBracket(350_001, 1_000_000, 0.0875),
                TaxBracket(1_000_001, -1, 0.0895)
            )
        ),
        State(
            "Florida",
            StateTaxMethod.NONE
        ),
        State(
            "Georgia",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 750, 0.01),
                TaxBracket(751, 2_250, 0.02),
                TaxBracket(2_251, 3_750, 0.03),
                TaxBracket(3_751, 5_250, 0.04),
                TaxBracket(5_251, 7_000, 0.05),
                TaxBracket(7_001, -1, 0.0575)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 1_000, 0.01),
                TaxBracket(1_001, 3_000, 0.02),
                TaxBracket(3_001, 5_000, 0.03),
                TaxBracket(5_001, 7_000, 0.04),
                TaxBracket(7_001, 10_000, 0.05),
                TaxBracket(10_001, -1, 0.0575)
            )
        ),
        State(
            "Hawaii",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 2_400, 0.014),
                TaxBracket(2_401, 4_800, 0.032),
                TaxBracket(4_801, 9_600, 0.055),
                TaxBracket(9_601, 14_400, 0.064),
                TaxBracket(14_401, 19_200, 0.068),
                TaxBracket(19_201, 24_000, 0.072),
                TaxBracket(24_001, 36_000, 0.076),
                TaxBracket(36_001, 48_000, 0.079),
                TaxBracket(48_001, 150_000, 0.0825),
                TaxBracket(150_001, 175_000, 0.09),
                TaxBracket(175_001, 200_000, 0.1),
                TaxBracket(200_001, -1, 0.11)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 4_800, 0.014),
                TaxBracket(4_801, 9_600, 0.032),
                TaxBracket(9_601, 19_200, 0.055),
                TaxBracket(19_201, 28_800, 0.064),
                TaxBracket(28_801, 38_400, 0.068),
                TaxBracket(38_401, 48_000, 0.072),
                TaxBracket(48_001, 72_000, 0.076),
                TaxBracket(72_001, 96_000, 0.079),
                TaxBracket(96_001, 300_000, 0.0825),
                TaxBracket(300_001, 350_000, 0.09),
                TaxBracket(350_001, 400_000, 0.1),
                TaxBracket(400_001, -1, 0.11)
            )
        ),
        State(
            "Idaho",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 1_568, 0.0113),
                TaxBracket(1_569, 3_136, 0.0313),
                TaxBracket(3_137, 4_704, 0.0363),
                TaxBracket(4_705, 6_272, 0.0463),
                TaxBracket(6_273, 7_840, 0.0563),
                TaxBracket(7_841, 11_760, 0.0663),
                TaxBracket(11_761, -1,0.0693 )
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 3_136, 0.0113),
                TaxBracket(3_137, 6_272, 0.0313),
                TaxBracket(6_273, 9_408, 0.0363),
                TaxBracket(9_409, 12_544, 0.0463),
                TaxBracket(12_545, 15_680, 0.0563),
                TaxBracket(15_681, 23_520, 0.0663),
                TaxBracket(23_521, -1,0.0693 )
            )
        ),
        State(
            "Illinois",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0495)
            )
        ),
        State(
            "Indiana",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0323)
            )
        ),
        State(
            "Iowa",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 1_676, 0.0033),
                TaxBracket(1_677, 3_352, 0.0067),
                TaxBracket(3_353, 6_704, 0.0225),
                TaxBracket(6_705, 15_084, 0.0414),
                TaxBracket(15_085, 25_140, 0.0563),
                TaxBracket(25_141, 33_520, 0.0596),
                TaxBracket(33_521, 50_280, 0.0625),
                TaxBracket(50_281, 75_420, 0.0744),
                TaxBracket(75_421, -1, 0.0853)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 1_676, 0.0033),
                TaxBracket(1_677, 3_352, 0.0067),
                TaxBracket(3_353, 6_704, 0.0225),
                TaxBracket(6_705, 15_084, 0.0414),
                TaxBracket(15_085, 25_140, 0.0563),
                TaxBracket(25_141, 33_520, 0.0596),
                TaxBracket(33_521, 50_280, 0.0625),
                TaxBracket(50_281, 75_420, 0.0744),
                TaxBracket(75_421, -1, 0.0853)
            )
        ),
        State(
            "Kansas",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 15_000, 0.031),
                TaxBracket(15_001, 30_000, 0.0525),
                TaxBracket(30_001, -1, 0.057)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 30_000, 0.031),
                TaxBracket(30_001, 60_000, 0.0525),
                TaxBracket(60_001, -1, 0.057)
            )
        ),
        State(
            "Kentucky",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.05)
            )
        ),
        State(
            "Louisiana",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 12_500, 0.02),
                TaxBracket(12_501, 50_000, 0.04),
                TaxBracket(50_001, -1, 0.06)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 25_000, 0.02),
                TaxBracket(25_001, 100_000, 0.04),
                TaxBracket(100_001, -1, 0.06)
            )
        ),
        State(
            "Maine",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 22_450, 0.058),
                TaxBracket(22_451, 53_150, 0.0675),
                TaxBracket(53_151, -1, 0.0715)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 44_950, 0.058),
                TaxBracket(44_951, 106_350, 0.0675),
                TaxBracket(106_351, -1, 0.0715)
            )
        ),
        State(
            "Maryland",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 1_000, 0.02),
                TaxBracket(1_001, 2_000, 0.03),
                TaxBracket(2_001, 3_000, 0.04),
                TaxBracket(3_001, 100_000, 0.0475),
                TaxBracket(100_001, 125_000, 0.05),
                TaxBracket(125_001, 150_000, 0.0525),
                TaxBracket(150_001, 250_000, 0.055),
                TaxBracket(250_001, -1, 0.0575)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 1_000, 0.02),
                TaxBracket(1_001, 2_000, 0.03),
                TaxBracket(2_001, 3_000, 0.04),
                TaxBracket(3_001, 150_000, 0.0475),
                TaxBracket(150_001, 175_000, 0.05),
                TaxBracket(175_001, 225_000, 0.0525),
                TaxBracket(225_001, 300_000, 0.055),
                TaxBracket(300_001, -1, 0.0575)
            )
        ),
        State(
            "Massachusetts",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.05)
            )
        ),
        State(
            "Michigan",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0425)
            )
        ),
        State(
            "Minnesota",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 27_230, 0.0535),
                TaxBracket(27_231, 89_440, 0.068),
                TaxBracket(89_441, 166_040, 0.0785),
                TaxBracket(166_041, -1, 0.0985)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 39_810, 0.0535),
                TaxBracket(39_811, 158_140, 0.068),
                TaxBracket(158_141, 276_200, 0.0785),
                TaxBracket(276_201, -1, 0.0985)
            )
        ),
        State(
            "Mississippi",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 4_000, 0.0),
                TaxBracket(4_001, 5_000, 0.03),
                TaxBracket(5_001, 10_000, 0.04),
                TaxBracket(10_001, -1, 0.05)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 4_000, 0.0),
                TaxBracket(4_001, 5_000, 0.03),
                TaxBracket(5_001, 10_000, 0.04),
                TaxBracket(10_001, -1, 0.05)
            )
        ),
        State(
            "Missouri",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 107, 0.0),
                TaxBracket(108, 1_073, 0.015),
                TaxBracket(1_074, 2_146, 0.02),
                TaxBracket(2_147, 3_219, 0.025),
                TaxBracket(3_220, 4_292, 0.03),
                TaxBracket(4_293, 5_365, 0.035),
                TaxBracket(5_366, 6_438, 0.04),
                TaxBracket(6_439, 7_511, 0.045),
                TaxBracket(7_512, 8_584, 0.05),
                TaxBracket(8_585, -1, 0.054)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 107, 0.0),
                TaxBracket(108, 1_073, 0.015),
                TaxBracket(1_074, 2_146, 0.02),
                TaxBracket(2_147, 3_219, 0.025),
                TaxBracket(3_220, 4_292, 0.03),
                TaxBracket(4_293, 5_365, 0.035),
                TaxBracket(5_366, 6_438, 0.04),
                TaxBracket(6_439, 7_511, 0.045),
                TaxBracket(7_512, 8_584, 0.05),
                TaxBracket(8_585, -1, 0.054)
            )
        ),
        State(
            "Montana",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 3_100, 0.01),
                TaxBracket(3_101, 5_500, 0.02),
                TaxBracket(5_501, 8_400, 0.03),
                TaxBracket(8_401, 11_300, 0.04),
                TaxBracket(11_301, 14_500, 0.05),
                TaxBracket(14_501, 18_700, 0.06),
                TaxBracket(18_701, -1, 0.069)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 3_100, 0.01),
                TaxBracket(3_101, 5_500, 0.02),
                TaxBracket(5_501, 8_400, 0.03),
                TaxBracket(8_401, 11_300, 0.04),
                TaxBracket(11_301, 14_500, 0.05),
                TaxBracket(14_501, 18_700, 0.06),
                TaxBracket(18_701, -1, 0.069)
            )
        ),
        State(
            "Nebraska",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 3_340, 0.0246),
                TaxBracket(3_341, 19_990, 0.0351),
                TaxBracket(19_991, 32_210, 0.0501),
                TaxBracket(32_211, -1, 0.0684)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 6_660, 0.0246),
                TaxBracket(6_661, 39_990, 0.0351),
                TaxBracket(39_991, 64_430, 0.0501),
                TaxBracket(64_431, -1, 0.0684)
            )
        ),
        State(
            "Nevada",
            StateTaxMethod.NONE
        ),
        State(
            "New Hampshire",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.05)
            )
        ),
        State(
            "New Jersey",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 20_000, 0.014),
                TaxBracket(20_001, 35_000, 0.0175),
                TaxBracket(35_001, 40_000, 0.035),
                TaxBracket(40_001, 75_000, 0.0553),
                TaxBracket(75_001, 500_000, 0.0637),
                TaxBracket(500_001, 1_000_000, 0.0897),
                TaxBracket(1_000_001, -1, 0.1075)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 20_000, 0.014),
                TaxBracket(20_001, 50_000, 0.0175),
                TaxBracket(50_001, 70_000, 0.0245),
                TaxBracket(70_001, 80_000, 0.035),
                TaxBracket(80_001, 150_000, 0.0553),
                TaxBracket(150_001, 500_000, 0.0637),
                TaxBracket(500_001, 1_000_000, 0.0897),
                TaxBracket(1_000_001, -1, 0.1075)
            )
        ),
        State(
            "New Mexico",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 5_500, 0.017),
                TaxBracket(5_501, 11_000, 0.032),
                TaxBracket(11_001, 16_000, 0.047),
                TaxBracket(16_001, 210_000, 0.049),
                TaxBracket(210_001, -1, 0.059)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 8_000, 0.017),
                TaxBracket(8_001, 16_000, 0.032),
                TaxBracket(16_001, 24_000, 0.047),
                TaxBracket(24_001, 315_000, 0.049),
                TaxBracket(315_001, -1, 0.059)
            )
        ),
        State(
            "New York",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 8_500, 0.04),
                TaxBracket(8_501, 11_700, 0.045),
                TaxBracket(11_701, 13_900, 0.0525),
                TaxBracket(13_901, 21_400, 0.059),
                TaxBracket(21_401, 80_650, 0.0597),
                TaxBracket(80_651, 215_400, 0.0633),
                TaxBracket(215_401, 1_077_550, 0.0685),
                TaxBracket(1_077_551, -1, 0.0882)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 17_150, 0.04),
                TaxBracket(17_151, 23_600, 0.045),
                TaxBracket(23_601, 27_900, 0.0525),
                TaxBracket(27_901, 43_000, 0.059),
                TaxBracket(43_001, 161_550, 0.0597),
                TaxBracket(161_551, 323_200, 0.0633),
                TaxBracket(323_201, 2_155_350, 0.0685),
                TaxBracket(2_155_351, -1, 0.0882)
            )
        ),
        State(
            "North Carolina",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0525)
            )
        ),
        State(
            "North Dakota",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 40_125, 0.011),
                TaxBracket(40_126, 97_150, 0.0204),
                TaxBracket(97_151, 202_650, 0.0227),
                TaxBracket(202_651, 440_600, 0.0264),
                TaxBracket(440_601, -1, 0.029)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 67_050, 0.011),
                TaxBracket(67_051, 161_950, 0.0204),
                TaxBracket(161_951, 246_700, 0.0227),
                TaxBracket(246_701, 440_600, 0.0264),
                TaxBracket(440_601, -1, 0.029)
            )
        ),
        State(
            "Ohio",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 22_150, 0.0),
                TaxBracket(22_151, 44_250, 0.0285),
                TaxBracket(44_251, 88_450, 0.0333),
                TaxBracket(88_451, 110_650, 0.038),
                TaxBracket(110_651, 221_300, 0.0441),
                TaxBracket(221_301, -1, 0.048)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 22_150, 0.0),
                TaxBracket(22_151, 44_250, 0.0285),
                TaxBracket(44_251, 88_450, 0.0333),
                TaxBracket(88_451, 110_650, 0.038),
                TaxBracket(110_651, 221_300, 0.0441),
                TaxBracket(221_301, -1, 0.048)
            )
        ),
        State(
            "Oklahoma",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 1_000, 0.005),
                TaxBracket(1_001, 2_500, 0.01),
                TaxBracket(2_501, 3_750, 0.02),
                TaxBracket(3_751, 4_900, 0.03),
                TaxBracket(4_901, 7_200, 0.04),
                TaxBracket(7_201, -1, 0.05)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 2_000, 0.005),
                TaxBracket(2_001, 5_000, 0.01),
                TaxBracket(5_001, 7_500, 0.02),
                TaxBracket(7_501, 9_800, 0.03),
                TaxBracket(9_801, 12_200, 0.04),
                TaxBracket(12_201, -1, 0.05)
            )
        ),
        State(
            "Oregon",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 3_650, 0.0475),
                TaxBracket(3_651, 9_200, 0.0675),
                TaxBracket(9_201, 125_000, 0.0875),
                TaxBracket(125_001, -1, 0.099)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 7_300, 0.0475),
                TaxBracket(7_301, 18_400, 0.0675),
                TaxBracket(18_401, 250_000, 0.0875),
                TaxBracket(250_001, -1, 0.099)
            )
        ),
        State(
            "Pennsylvania",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0307)
            )
        ),
        State(
            "Rhode Island",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 66_200, 0.0375),
                TaxBracket(66_201, 150_550, 0.0475),
                TaxBracket(150_551, -1, 0.0599)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 66_200, 0.0375),
                TaxBracket(66_201, 150_550, 0.0475),
                TaxBracket(150_551, -1, 0.0599)
            )
        ),
        State(
            "South Carolina",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 3_070, 0.0),
                TaxBracket(3_071, 6_150, 0.03),
                TaxBracket(6_151, 9_230, 0.04),
                TaxBracket(9_231, 12_310, 0.05),
                TaxBracket(12_311, 15_400, 0.06),
                TaxBracket(15_401, -1, 0.07)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 3_070, 0.0),
                TaxBracket(3_071, 6_150, 0.03),
                TaxBracket(6_151, 9_230, 0.04),
                TaxBracket(9_231, 12_310, 0.05),
                TaxBracket(12_311, 15_400, 0.06),
                TaxBracket(15_401, -1, 0.07)
            )
        ),
        State(
            "South Dakota",
            StateTaxMethod.NONE
        ),
        State(
            "Tennessee",
            StateTaxMethod.NONE
        ),
        State(
            "Texas",
            StateTaxMethod.NONE
        ),
        State(
            "Utah",
            StateTaxMethod.FLAT,
            listOf(
                TaxBracket(-1, -1, 0.0495)
            )
        ),
        State(
            "Vermont",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 40_350, 0.0335),
                TaxBracket(40_351, 97_800, 0.066),
                TaxBracket(97_801, 204_000, 0.076),
                TaxBracket(204_001, -1, 0.0875)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 67_450, 0.0335),
                TaxBracket(67_451, 163_000, 0.066),
                TaxBracket(163_001, 248_350, 0.076),
                TaxBracket(248_351, -1, 0.0875)
            )
        ),
        State(
            "Virginia",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 3_000, 0.02),
                TaxBracket(3_001, 5_000, 0.03),
                TaxBracket(5_001, 17_000, 0.05),
                TaxBracket(17_001, -1, 0.0575)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 3_000, 0.02),
                TaxBracket(3_001, 5_000, 0.03),
                TaxBracket(5_001, 17_000, 0.05),
                TaxBracket(17_001, -1, 0.0575)
            )
        ),
        State(
            "Washington",
            StateTaxMethod.NONE
        ),
        State(
            "West Virginia",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 10_000, 0.03),
                TaxBracket(10_001, 25_000, 0.04),
                TaxBracket(25_001, 40_000, 0.045),
                TaxBracket(40_001, 60_000, 0.06),
                TaxBracket(60_001, -1, 0.065)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 10_000, 0.03),
                TaxBracket(10_001, 25_000, 0.04),
                TaxBracket(25_001, 40_000, 0.045),
                TaxBracket(40_001, 60_000, 0.06),
                TaxBracket(60_001, -1, 0.065)
            )
        ),
        State(
            "Wisconsin",
            StateTaxMethod.PROGRESSIVE,
            taxBracketsSingle = listOf(
                TaxBracket(0, 12_120, 0.0354),
                TaxBracket(12_121, 24_250, 0.0465),
                TaxBracket(24_251, 266_930, 0.0627),
                TaxBracket(266_931, -1, 0.0765)
            ),
            taxBracketsMarried = listOf(
                TaxBracket(0, 16_160, 0.0354),
                TaxBracket(16_161, 32_330, 0.0465),
                TaxBracket(32_331, 355_910, 0.0627),
                TaxBracket(355_911, -1, 0.0765)
            )
        ),
        State(
            "Wyoming",
            StateTaxMethod.NONE
        )
    )

    fun getState(state: String): State {
        return states.first { it.name == state }
    }
}